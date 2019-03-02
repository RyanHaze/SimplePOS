package org.dgby.gatorpos.controllers;

import com.sun.javafx.scene.control.skin.FXVK;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.dgby.gatorpos.SceneManager;

import java.io.IOException;

public class PaymentScreenController {

    @FXML private TextField cashTendered_tf;

    public void initialize()
    {
        cashTendered_tf.getProperties().put(FXVK.VK_TYPE_PROP_KEY, "numeric");
    }

    // Employee button method
    public void backToCurrentTab(ActionEvent event) throws IOException {
        SceneManager.getInstance().activate("UserTransaction");


    }




}
