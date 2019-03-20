package org.dgby.gatorpos.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import sun.jvm.hotspot.gc.shared.CollectedHeap;
import javafx.event.ActionEvent;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserTransactionController {

    private List<Tab> tabList = new ArrayList<>();
    private Map<Tab, List<Button>> buttonMap = new HashMap<>();

    @FXML
    private TabPane tabPane;

    @FXML
    private Button ready_Button, done_Button, priceChange_Button, void_Button, reorder_Button, newcc_Button,
            storedcc_Button, exactCash_Button, tender_Button;

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
    }

    public void doneButtonPushed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Tab");
    }
}
