package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Tab;
import org.dgby.util.Track;

import static org.dgby.util.CCValidator.isValid;

public class TabScreenController {

    public static Tab currentTab = null;
    FilteredList<Tab> openTabs;
    private Timeline timeline = null;

    @FXML
    private ListView<Tab> listView;
    @FXML
    private TextField name_TF, cc_TF, expDate_TF;

    @FXML
    private Label mesg_Label;

    @FXML
    public void initialize() {
        Tab.updateTabs();
        openTabs = new FilteredList<>(Tab.getTabs(), tab -> tab.getCloseDate() == null);
        listView.setItems(openTabs);

        ChangeListener<String> changeListener = (obserable, oldVal, newVal) -> {
            if (!oldVal.equals(newVal) && newVal.length() > 3) {
                String startVal = newVal.substring(0, 2);
                String endVal = newVal.substring(newVal.length() - 1);

                // We count the end sentinals, to test if track 2 is there or not.
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
                            // NOTE: Make this an alert?
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
        mesg_Label.setText("");
    }

    public void fastCashPressed(ActionEvent event) throws IOException {
        String name = name_TF.getText();
        if (name.trim().length() == 0)
            name = "No Name";

        currentTab = Tab.openTab(name);
        SceneManager.getInstance().changeParent("UserTransaction");
        clearTF();
    }

    public void homePressed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }

    public void clearPressed(ActionEvent event) throws IOException {
        name_TF.clear();
        cc_TF.clear();
        expDate_TF.clear();
        name_TF.requestFocus();
    }

    public void openTabPressed(ActionEvent event) throws IOException {
        String name = name_TF.getText();
        String cc = cc_TF.getText();
        String expDate = expDate_TF.getText();

        // Only want to update tab card info if card exists
        if (cc.length() > 0) {
            if (isValid(cc)) {
                currentTab = Tab.openTab(name);
                Tab.updateTabCardInfo(currentTab, cc.substring(cc.length() - 4), name + ":" + cc + ":" + expDate);
                SceneManager.getInstance().changeParent("UserTransaction");
                clearTF();
            } else {
                displayMessage("Invalid CC", 2);
                cc_TF.selectAll();
                cc_TF.requestFocus();
            }
        }

        // Treat as fastCash if cc fields are empty.
        if (cc.length() == 0 && expDate.length() == 0)
            fastCashPressed(event);
    }

    // Clear the text fields
    private void clearTF() {
        name_TF.clear();
        cc_TF.clear();
        expDate_TF.clear();
    }

    private void displayMessage(String message, Integer seconds) {
        if (timeline != null)
            timeline.stop();

        timeline = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(mesg_Label.textProperty(), message)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(mesg_Label.textProperty(), "")));
        timeline.play();
    }

    public void selectTabPressed(ActionEvent event) throws IOException {
        if (!listView.getSelectionModel().isEmpty()) {
            currentTab = (Tab) listView.getSelectionModel().getSelectedItem();
            SceneManager.getInstance().changeParent("UserTransaction");
        } else {
            displayMessage("No Tab Selected!", 2);
        }
    }

    public void closeTabPressed(ActionEvent event) throws IOException {
        if (!listView.getSelectionModel().isEmpty()) {
            Tab selectedTab = (Tab) listView.getSelectionModel().getSelectedItem();
            Tab.closeTab(selectedTab);

            // Hacky >..>
            openTabs = new FilteredList<>(Tab.getTabs(), tab -> tab.getCloseDate() == null);
            listView.setItems(openTabs);
        } else {
            displayMessage("No Tab Selected!", 2);
        }
    }
}
