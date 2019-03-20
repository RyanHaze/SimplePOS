package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dgby.gatorpos.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TransactionManagerController {

    // todo: need to finalize database and create transaction class and define
    // tablieview and listview
    @FXML
    private Button showProduct_Button, ccInfo_Button, home_Button, back_Button;

    // Change scene to manager functions
    public void homeButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    public void managerButtonPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("MainManager");
    }

}
