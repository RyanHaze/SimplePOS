package org.dgby.gatorpos.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTransactionController {

    private List<Tab> tabList = new ArrayList<>();
    private Map<Tab, List<Button>> buttonMap = new HashMap<>();


    @FXML
    private TabPane tabPane;




    // Function buttons
    @FXML
    private Button ready_Button, done_Button, void_Button, reorder_Button, newcc_Button,
            storedcc_Button, exactCash_Button, tender_Button, tenFC_Button, twentyFC_Button,
            fiftyFC_Button,hundredFC_Button, clear_Button, unready_Button;

    // All the Labels
    @FXML
    private Label enterAmount_Label, total_Label, ccStored_Label, message_Label, change_Label, tabName_Label, date_Label;

    // All the Textfields
    @FXML
    private TextField amount_TF, ccNum_TF, expDate_TF;

    private Node[] disable_enable_paymentNodes;
    @FXML
    public void initialize() {

        Product.updateProducts();

        for (Tab tab : tabPane.getTabs()) {
            tabList.add(tab);
            AnchorPane pane = (AnchorPane) tab.getContent();
            GridPane childPane = (GridPane) pane.getChildren().get(0);

            List<Button> buttonList = new ArrayList<Button>();
            for (Node button : childPane.getChildren()) {
                button.setVisible(false);
                buttonList.add((Button) button);
            }

            buttonMap.put(tab, buttonList);
        }

        Map<String, Tab> categoryTabMap = new HashMap<>();
        List<String> productCategories = new ArrayList<>(Product.getCategories());
        Collections.sort(productCategories);
        Integer i = 0;
        for (Tab tab : tabList) {
            String category = "Tab " + i.toString();
            try {
                category = productCategories.get(i);
            } catch (IndexOutOfBoundsException e) {
            }
            tab.setText(category);
            categoryTabMap.put(category, tab);
            i++;
        }

        Map<Tab, Integer> indexMap = new HashMap<>();
        ObservableList<Product> productList = Product.getProducts();
        for (Product product : productList) {
            String productName = product.getName();
            String productCatagory = product.getCategory();

            Tab tab = categoryTabMap.getOrDefault(productCatagory, categoryTabMap.values().iterator().next());
            List<Button> buttonList = buttonMap.get(tab);

            Integer buttonIndex = indexMap.getOrDefault(tab, 0);
            indexMap.put(tab, buttonIndex + 1);

            Button button = buttonList.get(buttonIndex);

            button.setVisible(true);
            button.setText(productName);
        }

        // Create node array for disabline and enabling easier
        disable_enable_paymentNodes = new Node[]{storedcc_Button, newcc_Button, clear_Button, tender_Button,
                exactCash_Button, tenFC_Button, twentyFC_Button, fiftyFC_Button, hundredFC_Button, amount_TF, ccNum_TF, expDate_TF};
        disableNotReady();
    }

    // Done button
    public void doneButtonPushed(ActionEvent event) throws IOException {
        // TODO we need to disable and clear
        clearTF();
        disableNotReady();
        SceneManager.getInstance().changeParent("Tab");
    }
    // Ready Button Pushed
    public void readyButtonPushed(ActionEvent event) throws IOException{
        enableReady();
    }

    // Reoder Button Pushed
    public void reorderButtonPushed(ActionEvent event) throws IOException{
        // TODO When an item is selected in the lisview duplicate it
    }

    // void Button Pushed
    public void voidButtonPushed(ActionEvent event) throws IOException{
        // TODO When an item is selected delete it from the listview
    }

    // stored CC Pushed
    public void runStoredCCPushed(ActionEvent event) throws IOException{
        // TODO really this will just disable all other functions since we have it stored followed by a done button press
    }

    // new CC Pushed
    public void newCCPushed(ActionEvent event) throws IOException{
        // TODO this will store the new CC as the payment method. If a stored CC exists, it will replace it in the database
    }

    // any fast cash button can be pressed
    public void fastcashButtonPressed(ActionEvent event) throws IOException{
        // TODO this will read the text field from the exact cash button pressed and set the change label accordingly
    }

    // Tender button pushed
    public void tenderPressed(ActionEvent event){
        // TODO this will read the amount text field store it and set the change label accordingly
    }

    // Clear Button
    public void clearButtonPressed(ActionEvent event){
       clearTF();
    }

    // Undready? Button
    public void unreadyButtonPressed(ActionEvent event){
        disableNotReady();
    }

    // Disable buttons and text fields and set label text
    private void disableNotReady(){
        for (Node n : disable_enable_paymentNodes)
            n.setDisable(true);
    }

    // Enable Buttons and text fields and label
    private void enableReady(){
        for (Node n : disable_enable_paymentNodes)
            n.setDisable(false);
    }

    private void clearTF(){
        amount_TF.clear();
        ccNum_TF.clear();
        expDate_TF.clear();
    }
}
