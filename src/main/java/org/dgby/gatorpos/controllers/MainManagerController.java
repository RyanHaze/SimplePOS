package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import org.dgby.gatorpos.SceneManager;

public class MainManagerController {
    /*
     * This controller is for the first manager screen. This scene allows the user
     * to select a manager function and be brought to the corresponding scene
     */

    // declare the buttons
    @FXML
    private Button change_Add_Employees;
    @FXML
    private Button add_Remove_Items;
    @FXML
    private Button transactionHistory;
    @FXML
    private Button adjust_Timecards;
    @FXML
    private Button homeScreen;

    // loads a tableview of all employees
    public void change_Add_Employee(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("EmployeeManager");
    }

    public void back_to_home(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    public void change_Product_manager(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("ProductManager");
    }
}
