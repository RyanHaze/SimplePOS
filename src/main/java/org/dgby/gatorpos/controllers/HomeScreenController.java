package org.dgby.gatorpos.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;

import javafx.scene.input.TouchEvent;
import org.dgby.gatorpos.SceneManager;

public class HomeScreenController {
    /*
     * This class Controls the Home Screen. The home Screen consists of an Employee
     * and Manager Button that takes the user to the corresponding Screens.
     */

    // Declare the buttons (may not need to do this)
    @FXML
    private Button managerButton;
    @FXML
    private Button employeeButton;

    // initialize method
    @FXML
    public void initialize() {

    }

    // Employee button method
    public void employeeButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().activate("Tab");
    }

    public void managerButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().activate("MainManager");
    }

    public void exitButtonPressed(ActionEvent event )throws IOException
    {
        Platform.exit();
    }

    public void touchScreenPress (TouchEvent event) throws IOException
    {
        managerButton.setStyle("-fx-background-color: red ");
    }
}
