package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Map;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Tab;
import org.dgby.util.Track;

public class TabScreenController {

    public static Tab currentTab = null;

    @FXML
    private ListView<Tab> listView;
    @FXML
    private TextField name_TF, cc_TF, expDate_TF;

    @FXML
    ListView<Tab> ListView;

    @FXML
    public void initialize() {
        Tab.updateTabs();
        listView.setItems(Tab.getTabs());

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
        // TODO: this should also create a tab with a default named value
        SceneManager.getInstance().changeParent("UserTransaction");
    }

    public void homePressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    public void clear_Pressed(ActionEvent event) throws IOException {
        name_TF.clear();
        cc_TF.clear();
        expDate_TF.clear();
        name_TF.requestFocus();
    }

    public void start_Tab_Pressed(ActionEvent event) throws IOException {
        String name = name_TF.getText();
        String cc = cc_TF.getText();
        String expDate = expDate_TF.getText();

        if (name.trim().length() < 1)
            name = "No Name";

        currentTab = Tab.openTab(name);
        Tab.updateTabCardInfo(currentTab, cc.substring(cc.length() - 4), name + ":" + cc + ":" + expDate);

        SceneManager.getInstance().changeParent("UserTransaction");
    }

    public void goto_Selected_Tab(ActionEvent event) throws IOException {
        currentTab = (Tab) listView.getSelectionModel().getSelectedItem();
        SceneManager.getInstance().changeParent("UserTransaction");
    }

    public void delete_Selected_Tab(ActionEvent event) throws IOException {
        // TODO: Delete this? or implement a closeTab
    }
}
