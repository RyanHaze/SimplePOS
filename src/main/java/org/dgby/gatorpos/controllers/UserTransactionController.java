package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.TouchEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.event.*;

import java.io.IOException;
import java.util.Set;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;

public class UserTransactionController {
    @FXML
    private Button homeButton;
    @FXML
    private TabPane tabPane;

    @FXML
    private Button testButton;

    @FXML
    private TitledPane cart_tp;

    @FXML
    private Accordion cart_accord;




    @FXML
    public void initialize() {



        /*
        Product.updateProducts();
        Set<String> catagories = Product.getCategories();
        for (String catagory : catagories) {
            System.out.println("Creating " + catagory);
            Tab newTab = new Tab();
            newTab.setText(catagory);
            newTab.setContent(new Rectangle(200, 200, Color.LIGHTBLUE));
            tabPane.getTabs().add(newTab);


        }
    */
    }


    public void donePushed(ActionEvent event)
    {
        SceneManager.getInstance().changeParent("PaymentScreen");
    }
    public void home_screen(ActionEvent event) {
        SceneManager.getInstance().changeParent("Home");
    }

    public void touchScreenPress (TouchEvent event) throws IOException
    {

       testButton.setStyle("-fx-background-color: grey ");
    }
    public void touchScreenRelease (TouchEvent event) throws IOException
    {
        testButton.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400),\n" +
                "    linear-gradient(#ffef84, #f2ba44),\n" +
                "    linear-gradient(#ffea6a, #efaa22),\n" +
                "    linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "    linear-gradient(from 0% 0% to 15% 50%, rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0));");
    }
}
