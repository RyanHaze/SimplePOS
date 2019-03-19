package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import javafx.scene.control.TextField;
import org.dgby.gatorpos.SceneManager;

import org.dgby.util.Track;

public class TabScreenController {

    // todo: need to declare list view and obviously backend stuff
    @FXML
    private TextField name_TF, cc_TF, expDate_TF;

    @FXML
    public void initialize() {
        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.substring(0, 3).equals("%B")
                        || newValue.substring(oldValue.length() + 1, oldValue.length() + 3).equals("%B")) {
                    Map<String, String> ccMap = Track.track1Parser().parse(newValue);
                    name_TF.setText(ccMap.get("NAME"));
                    cc_TF.setText(ccMap.get("PAN"));
                    expDate_TF.setText(ccMap.get("ED"));
                }
            }
        };

        name_TF.textProperty().addListener(changeListener);
        cc_TF.textProperty().addListener(changeListener);
        expDate_TF.textProperty().addListener(changeListener);
    }

    public void fastCashPressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("UserTransaction");
    }

    public void homePressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }
}
