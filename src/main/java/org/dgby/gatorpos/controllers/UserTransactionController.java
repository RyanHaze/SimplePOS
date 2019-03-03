package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;




import org.dgby.gatorpos.SceneManager;

import java.io.IOException;


public class UserTransactionController {

    //todo declare everything too sleepy now

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

    public void doneButtonPushed(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Tab");
    }

    /*
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
    */
}
