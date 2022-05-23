package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import sample.instructions.*;

public class OpeningWindow {

    @FXML Button play;

    public void playClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gameplay/PlayOptions1.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void instructionsClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/instructions/GameOverview.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void aboutClicked(ActionEvent event) throws IOException {

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/About.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();*/

    }

    public void exitClicked(ActionEvent event) throws IOException {

        //Parent GO = FXMLLoader.load(getClass().getResource("GameOverview.fxml"));
        //Scene scene = new Scene(GO);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        //stage.setScene(scene);
        //stage.show();
        stage.close();

    }

}
