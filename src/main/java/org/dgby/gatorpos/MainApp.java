package org.dgby.gatorpos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
                SceneManager.addScene("Home", new Scene(
                                FXMLLoader.load(getClass().getResource("views/FinalHomeScreen.fxml")), 1000, 600));
                SceneManager.addScene("Tab",
                                new Scene(FXMLLoader.load(getClass().getResource("views/TabScreen.fxml")), 1000, 600));
                SceneManager.addScene("MainManager", new Scene(
                                FXMLLoader.load(getClass().getResource("views/MainManagerScreen.fxml")), 1000, 600));
                SceneManager.addScene("EmployeeManager", new Scene(
                                FXMLLoader.load(getClass().getResource("views/EmployeeManager.fxml")), 1000, 600));
                SceneManager.addScene("UserTransaction", new Scene(
                        FXMLLoader.load(getClass().getResource("views/UserTransaction.fxml")), 1000, 600));
                SceneManager.addScene("ProductManager", new Scene(
                        FXMLLoader.load(getClass().getResource("views/ProductManager.fxml")), 1000, 600));

                primaryStage.setTitle("GatorPOS");
                primaryStage.setFullScreen(false);
                SceneManager.getInstance().setStage(primaryStage);
                SceneManager.getInstance().activate("Home");
                primaryStage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }
}
