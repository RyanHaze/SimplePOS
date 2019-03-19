package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
        ChangeListener<String> changeListener = (obserable, oldVal, newVal) -> {
            if (!oldVal.equals(newVal) && newVal.length() > 10) {
                String startVal = newVal.substring(0, 2);
                String endVal = newVal.substring(newVal.length() - 1);

                if (startVal.equals("%B") && endVal.equals("?")) {
                    Map<String, String> ccMap = Track.track1Parser().parse(newVal);

                    if (ccMap.containsKey("DD")) {
                        Platform.runLater(() -> {
                            name_TF.setText(ccMap.get("NAME"));
                            cc_TF.setText(ccMap.get("PAN"));
                            expDate_TF.setText(ccMap.get("ED"));
                        });
                    }
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
