package org.dgby.gatorpos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {

                // create a parent when making a new "scene"
                Parent root_Home = FXMLLoader.load(getClass().getResource("views/FinalHomeScreen.fxml"));
                Parent root_Tab = FXMLLoader.load(getClass().getResource("views/TabScreen.fxml"));
                Parent root_MainManager = FXMLLoader.load(getClass().getResource("views/MainManagerScreen.fxml"));
                Parent root_EmployeeManager = FXMLLoader.load(getClass().getResource("views/EmployeeManager.fxml"));
                Parent root_UserTransaction = FXMLLoader.load(getClass().getResource("views/UserTransaction.fxml"));
                Parent root_ProductManager = FXMLLoader.load(getClass().getResource("views/ProductManager.fxml"));
                Parent root_PaymentScreen = FXMLLoader.load(getClass().getResource("views/PaymentScreen.fxml"));

                // add the parent to a hashmap for setting roots later
                SceneManager.addParent("Home", root_Home);
                SceneManager.addParent("Tab", root_Tab);
                SceneManager.addParent("MainManager", root_MainManager);
                SceneManager.addParent("EmployeeManager", root_EmployeeManager);
                SceneManager.addParent("UserTransaction", root_UserTransaction);
                SceneManager.addParent("ProductManager", root_ProductManager);
                SceneManager.addParent("PaymentScreen", root_PaymentScreen);

                // set the stage to fullscreen
                primaryStage.setTitle("GatorPOS");
                primaryStage.setFullScreen(true);
                SceneManager.getInstance().setStage(primaryStage);
                SceneManager.getInstance().createInitialScene("Home");
                primaryStage.show();
        }

        public static void main(String[] args) {

                System.setProperty("com.sun.javafx.isEmbedded", "true");
                System.setProperty("com.sun.javafx.touch", "true");
                System.setProperty("com.sun.javafx.virtualKeyboard", "javafx");
                launch(args);
        }
}
