package org.dgby.gatorpos.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;

import javafx.scene.control.Label;
import org.dgby.gatorpos.SceneManager;

public class HomeScreenController {

    /*
     * This class Controls the Home Screen. The home Screen consists of an Employee
     * and Manager Button that takes the user to the corresponding Screens. We will
     * also implement a clock system at a later time.
     */

    // Button declarations
    @FXML
    private Button manager_Button, employee_Button, clock_in_out_Button, exit_Button;
    // Label declarations
    @FXML
    private Label title_label, version_label, creator_label;

    // Nothing needs to be initialized on the home screen
    @FXML
    public void initialize() {
    }

    // Take user to employee scene
    public void employeeButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Tab");
    }

    // Take user to manager scene
    public void managerButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("MainManager");
    }

    // Exit the application
    public void exitButtonPressed(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /*
     * Todo: Maybe make the buttons highlight on text screen (think about) public
     * void touchScreenPress (TouchEvent event) throws IOException {
     * 
     * } public void touchScreenRelease (TouchEvent event) throws IOException {
     * 
     * }
     */

}
