package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.dgby.gatorpos.SceneManager;

import java.io.IOException;

public class PaymentScreenController {


    // Employee button method
    public void backToCurrentTab(ActionEvent event) throws IOException {
        SceneManager.getInstance().activate("UserTransaction");


    }


}
