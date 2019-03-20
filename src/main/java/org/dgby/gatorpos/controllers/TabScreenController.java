package org.dgby.gatorpos.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.dgby.gatorpos.SceneManager;

import org.dgby.gatorpos.models.Tab;
import org.dgby.util.Track;

public class TabScreenController {

    private String ccNum;
    private String grabbedName;
    private Date date;
    private SimpleDateFormat formatter;
    private String expdate;

    // todo: need to declare list view and obviously backend stuff
    @FXML
    private TextField name_TF, cc_TF, expDate_TF;

    @FXML
    ListView tabListView = new ListView();

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
        // Need to get the current date time
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();

        // Need name from name text field
        grabbedName = name_TF.getText();
        if (grabbedName.equals(""))
            grabbedName = "badName";
        // Check if the CC field is 16 digits
        if (cc_TF.getText().length() == 16) {
            ccNum = cc_TF.getText();
        }
        else{
            ccNum = null;
        }
        // Check if the date is 4 digits
        if (expDate_TF.getText().length() == 4)
        {
            expdate=expDate_TF.getText();
        }
        else{
            expdate = null;
        }

        // Info we have name, ccnum, expdate, datetime

        // so now populate the listview for testing purposes
        tabListView.getItems().addAll(grabbedName + "\t" + date + "\t" + ccNum + "\t" + expdate);

    }

    public void goto_Selected_Tab(ActionEvent event) throws IOException {
        // TODO: go to the selected tab in the listview


        // Here we need to get the selected tab and set the passed information
        //ObservableList selectedItem = tabListView.getSelectionModel().getSelectedIndices();



    }

    public void delete_Selected_Tab(ActionEvent event) throws IOException {
        // TODO: delete selected tab from the lsitview and the database ?? not sure if
        // we actually want this so dont worry for now
    }
}
