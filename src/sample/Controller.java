package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;

public class Controller {

    // button test
    @FXML
    private Button temp;

    @FXML
    private Button dragon_Beer;

    @FXML
    private Label tempLabel;


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




}
