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


public class EmployeeManagerController
{
    /* This class controls the employee/manager scene
       It presents a table view of all employees and
       access to either add/change/remove data and privileges.
    */

    @FXML private Button backButton;
    @FXML private Button homeButton;

    // The functions below manage changing to the previous screens

    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainManagerScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //call helper method to setstage
        setStage(event, tableViewParent, tableViewScene);

    }

    public void back_to_home(ActionEvent event) throws IOException
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
