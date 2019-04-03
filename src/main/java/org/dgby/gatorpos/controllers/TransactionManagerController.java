package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dgby.gatorpos.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TransactionManagerController {

    /* TODO: We need to populate the tableview with all finalized tabs
             Additionally, we need button functionality to show credit
             card information and the product tab.
     */

    @FXML
    private Button showProduct_Button, ccInfo_Button, home_Button, back_Button;

    // Change scene to manager functions
    public void homeButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    public void managerButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("MainManager");
    }


    public void showProductsPressed(ActionEvent event) throws  IOException
    {
        // TODO: This button displays all products from a particular tab
    }

    public void ccInfoPressed(ActionEvent event) throws IOException
    {
        //TODO: this button displays the cc information
    }

}
