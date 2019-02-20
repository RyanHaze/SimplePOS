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

public class HomeScreenController
{
    /* This class Controls the Home Screen. The home Screen
       consists of an Employee and Manager Button that takes
       the user to the corresponding Screens.
    */

    //Declare the buttons (may not need to do this)
    @FXML private Button managerButton;
    @FXML private Button employeeButton;

    //initialize method
    @FXML public void initialize()
    {
        
    }

    //Employee button method
    public void employeeButtonPressed(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("Tab");
    }

    public void managerButtonPressed(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("MainManager");
    }
}
