package sample.gameplay;
/*
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import sample.Main;
import sample.behindthescene.PlayerWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;*/
public class Loading {/*

public class Loading implements Initializable{

    public static boolean changeHobe = false;
    public ProgressBar progressBar ;
    BufferedReader br = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        {
            Parent pr = null;
            try {
                pr = FXMLLoader.load(getClass().getResource("/sample/gameplay/Trial.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(pr);
            String msg = "";
            *//*try {
                 msg = PlayOptions.getgc().inFromServer.readLine();
                System.out.println("after loadings readline");
            } catch (IOException e) {
                e.printStackTrace();
            }*//*

msg = "playerhoyegese";
            if(msg.equals("playerhoyegese"))
            {
                Stage stage = (Stage) progressBar.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    *//*public void takeme2MGS(ActionEvent event, boolean a)throws IOException
    {
        Parent pr = FXMLLoader.load(getClass().getResource("/sample/gameplay/Trial.fxml"));
        Scene scene = new Scene(pr);

        while (PlayOptions.playerhoyegese()==false) {
            System.out.println("Loop e aise");
            System.out.println(PlayOptions.playerhoyegese());
            //You Wait!
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }*//*
*/

}
