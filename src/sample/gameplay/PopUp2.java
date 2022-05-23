package sample.gameplay;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PopUp2 {
    public void sorryClicked(ActionEvent event)
    {
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.close();
    }
}
