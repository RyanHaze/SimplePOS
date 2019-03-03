package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.scene.control.TextField;
import org.dgby.gatorpos.SceneManager;

public class TabScreenController {

    //todo: need to declare list view and obviously backend stuff

    // Declare the Buttons
    @FXML
    private Button startCC_Button, fastCash_Button, selectedTab_Button, deleteTab_Button, home_Button;

    @FXML
    private TextField name_TF, cc_TF, expDate_TF;

    public void fastCashPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("UserTransaction");
    }

    public void homePressed(ActionEvent event) throws IOException{
        SceneManager.getInstance().changeParent("Home");
    }
}
