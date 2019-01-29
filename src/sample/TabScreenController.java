package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class TabScreenController
{

    @FXML private Button fastCash;


    public void fastCashTransaction(ActionEvent event) throws IOException
    {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserTransaction.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //call helper method to setstage
            setStage(event, tableViewParent, tableViewScene);
    }

    //helper method to get stage information
    private void setStage(ActionEvent event, Parent tableViewParent, Scene tableViewScene)
    {
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
    }





}
