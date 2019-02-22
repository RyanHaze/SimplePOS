package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.event.*;
import java.util.Set;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;

public class UserTransactionController {
    @FXML
    private Button homeButton;
    @FXML
    private TabPane tabPane;

    @FXML
    public void home_screen(ActionEvent event) {
        SceneManager.getInstance().activate("Home");
    }

    @FXML
    public void initialize() {
        Product.updateProducts();
        Set<String> catagories = Product.getCategories();
        for (String catagory : catagories) {
            System.out.println("Creating " + catagory);
            Tab newTab = new Tab();
            newTab.setText(catagory);
            newTab.setContent(new Rectangle(200, 200, Color.LIGHTBLUE));
            tabPane.getTabs().add(newTab);
        }
    }
}
