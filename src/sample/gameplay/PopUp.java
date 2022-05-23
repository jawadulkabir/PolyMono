package sample.gameplay;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUp {

    public void sorryClicked(ActionEvent event)
    {
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.close();
    }
}
