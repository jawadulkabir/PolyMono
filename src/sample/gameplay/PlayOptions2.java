package sample.gameplay;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class PlayOptions2 {
    static int howmanyplayers;

    @FXML
    Label no, loading;

    @FXML
    ProgressBar pb;

    @FXML
    Button two, three, four, back;

    Stage stage;

    public void takeme2MGS(ActionEvent event)throws IOException
    {
        Parent pr = FXMLLoader.load(getClass().getResource("/sample/gameplay/Trial.fxml"));
        Scene scene = new Scene(pr);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }



    /*public static boolean playerhoyegese()
    {
        if(howmanyplayers==2)
        {
            if(PlayerWorld.getP2()==2)
                return true;
        }

        else if(howmanyplayers==3)
        {
            if(PlayerWorld.getP3()==3)
                return true;
        }
        else if(howmanyplayers==4)
        {
            if(PlayerWorld.getP4()==4)
                return true;
        }
        return false;
    }*/









    /*public void two(ActionEvent event) {

        str = PlayOptions1.str + "2";//Hoyna :/
        ///SendToPlayerHandler
        try {
            s = new Socket("localhost", 61689);
            System.out.println("connected");
        } catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pr = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pr.println("C2");
        pr.flush();

        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();

    }*/












    public void twoClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 2;
        PlayOptions1.getgc().setKoyjonerMatch(2);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr hoilo   "+ numstr);
        PlayOptions1.getgc().getOutToServer().println(numstr);
        PlayOptions1.getgc().getOutToServer().flush();/*
        String a = PlayOptions1.getgc().inFromServer.readLine();
        int x = Integer.parseInt(a);
        PlayOptions1.getgc().setSerial(x);*/
        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }

    public void threeClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 3;
        PlayOptions1.getgc().setKoyjonerMatch(3);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr hoilo   "+ numstr);
        PlayOptions1.getgc().getOutToServer().println(numstr);
        PlayOptions1.getgc().getOutToServer().flush();
        /*String a = PlayOptions1.getgc().inFromServer.readLine();
        int x = Integer.parseInt(a);
        PlayOptions1.getgc().setSerial(x);*/
        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }
    public void fourClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 4;
        PlayOptions1.getgc().setKoyjonerMatch(4);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr hoilo   "+ numstr);
        PlayOptions1.getgc().getOutToServer().println(numstr);
        PlayOptions1.getgc().getOutToServer().flush();
        /*String a = PlayOptions1.getgc().inFromServer.readLine();
        int x = Integer.parseInt(a);
        PlayOptions1.getgc().setSerial(x);*/
        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }


    public void backbuttonClicked(ActionEvent event) throws IOException
    {
        Parent Start = FXMLLoader.load(getClass().getResource("/sample/OpeningWindow.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }



    public void changeScene() {

        no.setVisible(false); no.setDisable(true);
        two.setVisible(false);  two.setDisable(true);
        three.setVisible(false);  three.setDisable(true);
        four.setVisible(false);  four.setDisable(true);
        back.setVisible(false);  back.setDisable(true);

        loading.setVisible(true);
        pb.setVisible(true);    pb.setDisable(false);

        Thread th = new Thread(new Task< String >() {

            @Override
            protected String call() throws Exception {

                String str = "";

                ///WaitForServerMessage
                try {
                    System.out.println("br er jonno waiting");
                    str = PlayOptions1.getgc().getInFromServer().readLine();
                    System.out.println(str + "pailam");

                    PlayOptions1.getgc().getOutToServer().println("Match Paisi!");
                    PlayOptions1.getgc().getOutToServer().flush();
                } catch (Exception e)
                {
                    System.err.println(e);
                }

                Thread.sleep(1018); //damian forever //11701018

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gameplay/Trial.fxml"));
                Region region = loader.load();
                Group group = new Group(region);
                StackPane pane = new StackPane();
                pane.getChildren().add(group);
                Scene scene = new Scene(pane);
                group.scaleXProperty().bind(scene.widthProperty().divide(1280));
                group.scaleYProperty().bind(scene.heightProperty().divide(720));


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.setScene(scene);
                        stage.show();
                    }
                });

                return "Hudai";
            }
        });

        th.setDaemon(true);
        th.start();

    }

}