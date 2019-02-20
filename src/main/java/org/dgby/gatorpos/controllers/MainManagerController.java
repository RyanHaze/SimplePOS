package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import org.dgby.gatorpos.SceneManager;

public class MainManagerController
{
    /* This controller is for the first manager screen.
       This scene allows the user to select a manager function
       and be brought to the corresponding scene
    */

    //declare the buttons
    @FXML private Button change_Add_Employees;
    @FXML private Button add_Remove_Items;
    @FXML private Button transactionHistory;
    @FXML private Button adjust_Timecards;
    @FXML private Button homeScreen;

    //loads a tableview of all employees
    public void change_Add_Employee(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("EmployeeManager");
    }

    public void back_to_home(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("Home");
    }
}
