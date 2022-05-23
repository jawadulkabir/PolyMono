package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.rmi.registry.Registry;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("OpeningWindow.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OpeningWindow.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        stage.setTitle("Hello World");
        stage.getIcons().add(new Image("file:GameIcon.png"));

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        stage.setScene(scene);
        stage.show();


        /*Parent root = FXMLLoader.load(getClass().getResource("OpeningWindow.fxml"));
        stage.setTitle("Hello World");
        stage.getIcons().add(new Image("file:GameIcon.png"));
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/
    }

    public static void main(String[] args) {

        launch(args);

    }
}