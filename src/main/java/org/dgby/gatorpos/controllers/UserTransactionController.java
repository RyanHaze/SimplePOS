package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;




import org.dgby.gatorpos.SceneManager;

import java.io.IOException;


public class UserTransactionController {

    //todo declare everything too sleepy now


    @FXML
    private Tab cat1_Tab, cat2_Tab, cat3_Tab, cat4_Tab, cat5_Tab, cat6_Tab, cat7_Tab, cat8_Tab, cat9_Tab;

    // declare all tab buttons
    @FXML
    private Button cat1_but1_Button, cat1_but2_Button, cat1_but3_Button, cat1_but4_Button, cat1_but5_Button,
            cat1_but6_Button, cat1_but7_Button, cat1_but8_Button, cat1_but9_Button, cat1_but10_Button,
            cat1_but11_Button, cat1_but12_Button, cat1_but13_Button, cat1_but14_Button, cat1_but15_Button,
            cat1_but16_Button, cat1_but17_Button, cat1_but18_Button, cat1_but19_Button, cat1_but20_Button,
            cat1_but21_Button, cat1_but22_Button, cat1_but23_Button, cat1_but24_Button, cat1_but25_Button,
            cat1_but26_Button, cat1_but27_Button, cat1_but28_Button, cat1_but29_Button, cat1_but30_Button,
            cat1_but31_Button, cat1_but32_Button, cat1_but33_Button, cat1_but34_Button, cat1_but35_Button, cat1_but36_Button,
            cat2_but1_Button, cat2_but2_Button, cat2_but3_Button, cat2_but4_Button, cat2_but5_Button,
            cat2_but6_Button, cat2_but7_Button, cat2_but8_Button, cat2_but9_Button, cat2_but10_Button,
            cat2_but11_Button, cat2_but12_Button, cat2_but13_Button, cat2_but14_Button, cat2_but15_Button,
            cat2_but16_Button, cat2_but17_Button, cat2_but18_Button, cat2_but19_Button, cat2_but20_Button,
            cat2_but21_Button, cat2_but22_Button, cat2_but23_Button, cat2_but24_Button, cat2_but25_Button,
            cat2_but26_Button, cat2_but27_Button, cat2_but28_Button, cat2_but29_Button, cat2_but30_Button,
            cat2_but31_Button, cat2_but32_Button, cat2_but33_Button, cat2_but34_Button, cat2_but35_Button, cat2_but36_Button,
            cat3_but1_Button, cat3_but2_Button, cat3_but3_Button, cat3_but4_Button, cat3_but5_Button,
            cat3_but6_Button, cat3_but7_Button, cat3_but8_Button, cat3_but9_Button, cat3_but10_Button,
            cat3_but11_Button, cat3_but12_Button, cat3_but13_Button, cat3_but14_Button, cat3_but15_Button,
            cat3_but16_Button, cat3_but17_Button, cat3_but18_Button, cat3_but19_Button, cat3_but20_Button,
            cat3_but21_Button, cat3_but22_Button, cat3_but23_Button, cat3_but24_Button, cat3_but25_Button,
            cat3_but26_Button, cat3_but27_Button, cat3_but28_Button, cat3_but29_Button, cat3_but30_Button,
            cat3_but31_Button, cat3_but32_Button, cat3_but33_Button, cat3_but34_Button, cat3_but35_Button, cat3_but36_Button,
            cat4_but1_Button, cat4_but2_Button, cat4_but3_Button, cat4_but4_Button, cat4_but5_Button,
            cat4_but6_Button, cat4_but7_Button, cat4_but8_Button, cat4_but9_Button, cat4_but10_Button,
            cat4_but11_Button, cat4_but12_Button, cat4_but13_Button, cat4_but14_Button, cat4_but15_Button,
            cat4_but16_Button, cat4_but17_Button, cat4_but18_Button, cat4_but19_Button, cat4_but20_Button,
            cat4_but21_Button, cat4_but22_Button, cat4_but23_Button, cat4_but24_Button, cat4_but25_Button,
            cat4_but26_Button, cat4_but27_Button, cat4_but28_Button, cat4_but29_Button, cat4_but30_Button,
            cat4_but31_Button, cat4_but32_Button, cat4_but33_Button, cat4_but34_Button, cat4_but35_Button, cat4_but36_Button,
            cat5_but1_Button, cat5_but2_Button, cat5_but3_Button, cat5_but4_Button, cat5_but5_Button,
            cat5_but6_Button, cat5_but7_Button, cat5_but8_Button, cat5_but9_Button, cat5_but10_Button,
            cat5_but11_Button, cat5_but12_Button, cat5_but13_Button, cat5_but14_Button, cat5_but15_Button,
            cat5_but16_Button, cat5_but17_Button, cat5_but18_Button, cat5_but19_Button, cat5_but20_Button,
            cat5_but21_Button, cat5_but22_Button, cat5_but23_Button, cat5_but24_Button, cat5_but25_Button,
            cat5_but26_Button, cat5_but27_Button, cat5_but28_Button, cat5_but29_Button, cat5_but30_Button,
            cat5_but31_Button, cat5_but32_Button, cat5_but33_Button, cat5_but34_Button, cat5_but35_Button, cat5_but36_Button,
            cat6_but1_Button, cat6_but2_Button, cat6_but3_Button, cat6_but4_Button, cat6_but5_Button,
            cat6_but6_Button, cat6_but7_Button, cat6_but8_Button, cat6_but9_Button, cat6_but10_Button,
            cat6_but11_Button, cat6_but12_Button, cat6_but13_Button, cat6_but14_Button, cat6_but15_Button,
            cat6_but16_Button, cat6_but17_Button, cat6_but18_Button, cat6_but19_Button, cat6_but20_Button,
            cat6_but21_Button, cat6_but22_Button, cat6_but23_Button, cat6_but24_Button, cat6_but25_Button,
            cat6_but26_Button, cat6_but27_Button, cat6_but28_Button, cat6_but29_Button, cat6_but30_Button,
            cat6_but31_Button, cat6_but32_Button, cat6_but33_Button, cat6_but34_Button, cat6_but35_Button, cat6_but36_Button,
            cat7_but1_Button, cat7_but2_Button, cat7_but3_Button, cat7_but4_Button, cat7_but5_Button,
            cat7_but6_Button, cat7_but7_Button, cat7_but8_Button, cat7_but9_Button, cat7_but10_Button,
            cat7_but11_Button, cat7_but12_Button, cat7_but13_Button, cat7_but14_Button, cat7_but15_Button,
            cat7_but16_Button, cat7_but17_Button, cat7_but18_Button, cat7_but19_Button, cat7_but20_Button,
            cat7_but21_Button, cat7_but22_Button, cat7_but23_Button, cat7_but24_Button, cat7_but25_Button,
            cat7_but26_Button, cat7_but27_Button, cat7_but28_Button, cat7_but29_Button, cat7_but30_Button,
            cat7_but31_Button, cat7_but32_Button, cat7_but33_Button, cat7_but34_Button, cat7_but35_Button, cat7_but36_Button,
            cat8_but1_Button, cat8_but2_Button, cat8_but3_Button, cat8_but4_Button, cat8_but5_Button,
            cat8_but6_Button, cat8_but7_Button, cat8_but8_Button, cat8_but9_Button, cat8_but10_Button,
            cat8_but11_Button, cat8_but12_Button, cat8_but13_Button, cat8_but14_Button, cat8_but15_Button,
            cat8_but16_Button, cat8_but17_Button, cat8_but18_Button, cat8_but19_Button, cat8_but20_Button,
            cat8_but21_Button, cat8_but22_Button, cat8_but23_Button, cat8_but24_Button, cat8_but25_Button,
            cat8_but26_Button, cat8_but27_Button, cat8_but28_Button, cat8_but29_Button, cat8_but30_Button,
            cat8_but31_Button, cat8_but32_Button, cat8_but33_Button, cat8_but34_Button, cat8_but35_Button, cat8_but36_Button,
            cat9_but1_Button, cat9_but2_Button, cat9_but3_Button, cat9_but4_Button, cat9_but5_Button,
            cat9_but6_Button, cat9_but7_Button, cat9_but8_Button, cat9_but9_Button, cat9_but10_Button,
            cat9_but11_Button, cat9_but12_Button, cat9_but13_Button, cat9_but14_Button, cat9_but15_Button,
            cat9_but16_Button, cat9_but17_Button, cat9_but18_Button, cat9_but19_Button, cat9_but20_Button,
            cat9_but21_Button, cat9_but22_Button, cat9_but23_Button, cat9_but24_Button, cat9_but25_Button,
            cat9_but26_Button, cat9_but27_Button, cat9_but28_Button, cat9_but29_Button, cat9_but30_Button,
            cat9_but31_Button, cat9_but32_Button, cat9_but33_Button, cat9_but34_Button, cat9_but35_Button, cat9_but36_Button;



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
