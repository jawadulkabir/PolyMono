package sample.gameplay;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.IOException;

public class AlertBox {

    //Create variable
    static boolean answer = false;
    static boolean clicked;
    static Stage window;
    static Button yesButton;
    static Button noButton;

    public void display(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(305); window.setResizable(false);
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #002040");


        if(message.contains("To"))  //property buy, rent pay, tax pay...
        {
            Label label = new Label();
            String message1;
            if(message.contains("Buy")) message1 = message.substring(0,"Do You Want To Buy This Property?".length());
            else message1 = message;
            label.setText(message1);
            label.setStyle("-fx-font-size: 20");
            label.setStyle("-fx-text-fill: white");

            window.setOnCloseRequest(event ->
            {
                event.consume();
                Stage stage = new Stage();
                Parent root = null;
                try {
                    if(!message.contains("Pay"))
                    root = FXMLLoader.load(getClass().getResource("PopUp.fxml"));
                    else root = FXMLLoader.load(getClass().getResource("PopUp2.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Hello World");
                stage.setScene(new Scene(root, 300, 200));
                stage.show();
            });


            //Create two buttons
            yesButton = new Button("YES");
            if(!message.contains("Pay"))
            {
                noButton = new Button("NO");
                layout.getChildren().addAll(label, yesButton, noButton);
            }

            else{
                yesButton.setText("OK");
                layout.getChildren().addAll(label, yesButton);
            }


//Clicking will set answer and close window
            yesButton.setOnAction(e -> {
                clicked = true;
                System.out.println(
                        "trye jirtesi"
                );
                answer  = true;
                if(message.contains("Buy"))
                {
                    int x = Integer.parseInt(message.substring("Do You Want To Buy This Property?".length()));
                    PlayOptions1.getgc().getAmarJayga().set(x-1,1);
                    alertboxSaysHi("ok buy");
                }
                else if(message.contains("Pay")) alertboxSaysHi("ok pay");
                else if(message.contains("Build")) alertboxSaysHi("hmm boshabo");
                window.close();
            });

            if(!message.contains("Pay"))
            {
                noButton.setOnAction(e -> {
                    clicked = true;
                    answer  = false;
                    alertboxSaysHi("me no buy");
                    window.close();
                });
            }

        }

        else if(message.contains("vaggo") || message.contains("sujog"))
        {
            String imgname = null;

            if(message.contains("vaggo"))  imgname = "vaggo";
            else  imgname = "sujog";

            Button ok = new Button("OK");
            String newmsg[] = new String[2];
            newmsg =  message.split("#");

            String vv =newmsg[1];
            int v = Integer.parseInt(vv);

            window.setOnCloseRequest(event ->
            {
                event.consume();
                Stage stage = new Stage();
                Parent root = null;
                try {
                        root = FXMLLoader.load(getClass().getResource("PopUp2.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Hello World");
                stage.setScene(new Scene(root, 300, 200));
                stage.show();
            });

            {
                ImageView img = new ImageView(new Image("file:"+imgname+v+".png"));
                img.setFitWidth(350);
                img.setFitHeight(200);
                layout.getChildren().addAll(img, ok);
            }

            ok.setOnAction(e ->
            {
                alertboxSaysHi(vv);
                window.close();
            });

        }


        /*else if( message.contains("sujog"))
        {
            String imgname = null;

            if(message.contains("vaggo"))  imgname = "vaggo";
            else  imgname = "sujog";

            Button ok = new Button("OK");
            String newmsg[] = new String[2];
            newmsg =  message.split("#");

            String vv =newmsg[1];
            int v = Integer.parseInt(vv);


            {
                ImageView img = new ImageView(new Image("file:"+imgname+v+".png"));
                img.setFitWidth(350);
                img.setFitHeight(200);
                layout.getChildren().addAll(img, ok);
            }

            ok.setOnAction(e ->
            {
                alertboxSaysHi(vv);
                window.close();
            });

        }*/



        else if(message.contains("jatra shuru"))
        {
            Label label1 = new Label();
            Label label2 = new Label();
            label1.setText("You have passed GO");
            label2.setText("Please collect BDT 200 from the bank");
            label1.setStyle("-fx-text-fill: white");
            label2.setStyle("-fx-text-fill: white");

            Button collect = new Button("Collect");

            window.setOnCloseRequest(event ->
            {
                event.consume();
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("PopUp2.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Hello World");
                stage.setScene(new Scene(root, 300, 200));
                stage.show();
            });

            layout.getChildren().addAll(label1, label2, collect);

            collect.setOnAction(e -> {
                alertboxSaysHi("ok collect");
                window.close();
            });

        }

        else if(message.contains("Construct"))
        {
            Button ok = new Button("OK");
            Label label1 = new Label();
            label1.setText(message);
            label1.setStyle("-fx-text-fill: white");

            layout.getChildren().addAll(label1, ok);


            ok.setOnAction(e ->
            {
                window.close();
            });

        }




        //Add buttons
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer

    }

    public void alertboxSaysHi(String s)
    {
        PlayOptions1.getgc().getOutToServer().println(s);
        PlayOptions1.getgc().getOutToServer().flush();
    }

}