package sample.instructions;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverview {

    public void backbuttonClicked(ActionEvent event) throws IOException
    {
        Parent Start = FXMLLoader.load(getClass().getResource("/sample/OpeningWindow.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}
