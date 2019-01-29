package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UserTransactionController {

    // button test
    @FXML private Button temp;
    @FXML private Button dragon_Beer;
    @FXML private Label tempLabel;
    @FXML private Button homeButton;



    @FXML
    private void onAction(ActionEvent event)
    {
        tempLabel.setText("Credit card button");
    }

    @FXML
    private void onAction2(ActionEvent event)
    {
        tempLabel.setText("Dragon Beer added to cart");
        //store more in the database


    }

    public void home_screen(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //call helper method to setstage
        setStage(event, tableViewParent, tableViewScene);
    }

    //helper method to reduce code redundancy
    //helper method to get stage information
    private void setStage(ActionEvent event, Parent tableViewParent, Scene tableViewScene)
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }






}
