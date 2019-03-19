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
            if (!oldVal.equals(newVal) && newVal.length() > 3) {
                String startVal = newVal.substring(0, 2);
                String endVal = newVal.substring(newVal.length() - 1);

                // We count the question marks, to test if track 2 is there or not.
                long q_count = newVal.chars().filter(ch -> ch == '?').count();

                // q_count must be >= to 2, This means track 2 is also in the input.
                if (q_count >= 2 && endVal.equals("?")) {
                    if (startVal.equals("%B")) {
                        Map<String, String> ccMap = Track.track1Parser().parse(newVal);

                        if (ccMap.containsKey("DD")) {
                            Platform.runLater(() -> {
                                name_TF.setText(ccMap.get("NAME"));
                                cc_TF.setText(ccMap.get("PAN"));
                                expDate_TF.setText(ccMap.get("ED"));
                            });
                        }
                    }

                    if (startVal.equals("%E")) {
                        Platform.runLater(() -> {
                            // TODO: Make this an alert!
                            name_TF.setText("Swipe Error, Try Again!");
                            name_TF.selectAll();
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
