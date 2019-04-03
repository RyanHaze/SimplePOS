package org.dgby.gatorpos.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;
import javafx.event.ActionEvent;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;
import org.dgby.util.Track;
import static org.dgby.util.CCValidator.isValid;
import java.io.IOException;
import java.util.*;

public class UserTransactionController {

    private List<Tab> tabList = new ArrayList<>();
    private Map<Tab, List<Button>> buttonMap = new HashMap<>();

    @FXML
    private TabPane tabPane;

    // Function buttons
    @FXML
    private Button ready_Button, done_Button, void_Button, reorder_Button, newcc_Button, storedcc_Button,
            exactCash_Button, tender_Button, tenFC_Button, twentyFC_Button, fiftyFC_Button, hundredFC_Button,
            clear_Button, unready_Button;

    // All the Labels
    @FXML
    private Label enterAmount_Label, total_Label, ccStored_Label, message_Label, change_Label, tabName_Label,
            date_Label;

    // All the Textfields
    @FXML
    private TextField amount_TF, ccNum_TF, expDate_TF;

    @FXML
    private ListView<Pair<Product, Integer>> productList;

    private Node[] disable_enable_paymentNodes;

    @FXML
    public void initialize() {
        TabScreenController.currentTab.addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                tabName_Label.setText(newValue.getNote());
                date_Label.setText(newValue.getOpenDate().toString());
                ccStored_Label.setText(newValue.getCardLastFour());
                total_Label.setText("$ " + newValue.getTotal());

                productList.setItems(new FilteredList<>(newValue.getProducts(), product -> product.getValue() > 0));
            }
        });
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
            button.setOnAction((event) -> {
                org.dgby.gatorpos.models.Tab theTab = TabScreenController.currentTab.get();
                ObservableList<Pair<Product, Integer>> products = theTab.getProducts();

                Integer currentCount = 0;
                for (Pair<Product, Integer> item : products) {
                    if (item.getKey().getId() == product.getId()) {
                        currentCount = item.getValue();
                    }
                }

                org.dgby.gatorpos.models.Tab.updateTabProduct(theTab, product, currentCount + 1);
                total_Label.setText("$ " + theTab.getTotal());
            });
        }

        // Create node array for disabline and enabling easier
        disable_enable_paymentNodes = new Node[] {
                storedcc_Button, newcc_Button, clear_Button, tender_Button, exactCash_Button, tenFC_Button,
                twentyFC_Button, fiftyFC_Button, hundredFC_Button, amount_TF, ccNum_TF, expDate_TF
        };
        disableNotReady();

        // Maybe util lol not cc parser??!
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
                                ccNum_TF.setText(ccMap.get("PAN"));
                                expDate_TF.setText(ccMap.get("ED"));
                            });
                        }
                    }

                    if (startVal.equals("%E")) {
                        Platform.runLater(() -> {
                            // NOTE: Make this an alert?
                            ccNum_TF.setText("Swipe Error, Try Again!");
                            ccNum_TF.selectAll();
                        });
                    }
                }
            }
        };

        ccNum_TF.textProperty().addListener(changeListener);
        expDate_TF.textProperty().addListener(changeListener);
    }

    // Done button
    public void doneButtonPushed(ActionEvent event) throws IOException {
        clearTF();
        disableNotReady();
        SceneManager.getInstance().changeParent("Tab");
    }

    // Ready Button Pushed
    public void readyButtonPushed(ActionEvent event) throws IOException {
        enableReady();
    }

    // Reoder Button Pushed
    public void reorderButtonPushed(ActionEvent event) throws IOException {
        if (!productList.getSelectionModel().isEmpty()) {
            Pair<Product, Integer> selectedItem = productList.getSelectionModel().getSelectedItem();
            Product product = selectedItem.getKey();
            Integer currentCount = selectedItem.getValue();
            org.dgby.gatorpos.models.Tab.updateTabProduct(TabScreenController.currentTab.get(), product,
                    currentCount + 1);

        }

    }

    // void Button Pushed
    public void voidButtonPushed(ActionEvent event) throws IOException {
        if (!productList.getSelectionModel().isEmpty()) {
            Pair<Product, Integer> selectedItem = productList.getSelectionModel().getSelectedItem();
            Product product = selectedItem.getKey();
            org.dgby.gatorpos.models.Tab.updateTabProduct(TabScreenController.currentTab.get(), product, 0);
        }
    }

    // stored CC Pushed
    public void runStoredCCPushed(ActionEvent event) throws IOException {

        // Make sure to card is stored if its not tell em and do nothing
        if (TabScreenController.currentTab.get().getCardData() != null)
        {
            org.dgby.gatorpos.models.Tab.closeTab(TabScreenController.currentTab.get());
            message_Label.setText("Transaction complete! Press Done!");
            disableNotReady();
        }
        else
        {
            message_Label.setText("No Stored Credit card");
        }
    }

    // new CC Pushed
    public void newCCPushed(ActionEvent event) throws IOException {
        // Updates card info and either fills in or replaces the card information
        String cc = ccNum_TF.getText();
        String expdate =  expDate_TF.getText();

        if(isValid(cc)){
            org.dgby.gatorpos.models.Tab.updateTabCardInfo(TabScreenController.currentTab.get(), cc.substring(cc.length()-4),
                    "no Name" + cc + expdate );
            disableNotReady();
            message_Label.setText("Transaction complete! Press Done!");
            org.dgby.gatorpos.models.Tab.closeTab(TabScreenController.currentTab.get());
        }
        else
        {
            message_Label.setText("Invalid Credit Card Number");
        }

    }

    // any fast cash button can be pressed
    public void fastcashButtonPressed(ActionEvent event) throws IOException {
        change_Label.setText("$ 0.00");
        message_Label.setText("Exact cash Processed! Press Done!");
        org.dgby.gatorpos.models.Tab.closeTab(TabScreenController.currentTab.get());
        disableNotReady();
    }

    // Tender button pushed
    public void tenderPressed(ActionEvent event) {
        String total = total_Label.getText();
        Float tot = Float.parseFloat(total.substring(2));

        if(!amount_TF.getText().isEmpty())
        {
            Float amnt = Float.parseFloat(amount_TF.getText());

            if(amnt < tot)
            {
                message_Label.setText("Not enough Cash!");
            }
            else
            {
                org.dgby.gatorpos.models.Tab.closeTab(TabScreenController.currentTab.get());
                message_Label.setText("Click done to finish Transaction");
                Float change = amnt - tot;
                String change_Togive = change.toString();
                change_Label.setText(change_Togive);
                disableNotReady();
            }

        }
        else
        {
            message_Label.setText("You must enter an amount");
        }
    }

    // Clear Button
    public void clearButtonPressed(ActionEvent event) {
        clearTF();
    }

    // Undready? Button
    public void unreadyButtonPressed(ActionEvent event) {
        disableNotReady();
    }

    // Disable buttons and text fields and set label text
    private void disableNotReady() {
        for (Node n : disable_enable_paymentNodes)
            n.setDisable(true);
    }

    // Enable Buttons and text fields and label
    private void enableReady() {
        for (Node n : disable_enable_paymentNodes)
            n.setDisable(false);
    }

    private void clearTF() {
        amount_TF.clear();
        ccNum_TF.clear();
        expDate_TF.clear();
        ccNum_TF.clear();
        expDate_TF.clear();
        amount_TF.clear();
        change_Label.setText("Change: $ xx.xx");
        message_Label.setText("Message:");
    }
}
