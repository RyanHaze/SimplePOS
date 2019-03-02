package org.dgby.gatorpos.controllers;

import com.sun.javafx.scene.control.skin.FXVK;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.TouchEvent;
import org.dgby.gatorpos.SceneManager;

import java.io.IOException;

public class PaymentScreenController {

    @FXML private TextField cashTendered_tf;

    public void initialize()
    {

    }

    // Employee button method
    public void backToCurrentTab(ActionEvent event) throws IOException {
        SceneManager.getInstance().activate("UserTransaction");


    }

    public void enter_Cash (ActionEvent event) throws  IOException{
        cashTendered_tf.getProperties().put(FXVK.VK_TYPE_PROP_KEY, "numeric");
    }
    public void touch_Cash (TouchEvent event) throws IOException{

        cashTendered_tf.getProperties().put(FXVK.VK_TYPE_PROP_KEY, "numeric");
    }


}
