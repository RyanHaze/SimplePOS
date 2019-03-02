package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import org.dgby.gatorpos.SceneManager;

public class TabScreenController {
    @FXML
    private Button fastCash;

    public void fastCashTransaction(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("UserTransaction");
    }
}
