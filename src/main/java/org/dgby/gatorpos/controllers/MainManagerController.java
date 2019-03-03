package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.scene.control.Label;
import org.dgby.gatorpos.SceneManager;

public class MainManagerController {
    /*
     * This controller is for the first manager screen. This scene allows the user
     * to select a manager function and be brought to the corresponding scene
     */

    // declare the buttons
    @FXML
    private Button change_Add_Employees_Button, change_Add_Items_Button, transaction_Manager_Button, adjust_Timecards_Button, home_Button;

    //declare the labels
    @FXML
    private Label manager_Functions_Label;

    // change scene to EmployeeManager
    public void change_Add_Employee(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("EmployeeManager");
    }

    // Go back to Home scene
    public void back_to_home(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    // change scene to ProductManager
    public void change_Product_manager(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("ProductManager");
    }

    //change scene to TransactionManager
    public void trans_Manager_Pressed(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("TransactionManager");
    }
}
