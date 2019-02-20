package org.dgby.gatorpos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager.addScene("Home",
                new Scene(FXMLLoader.load(getClass().getResource("views/HomeScreen.fxml")), 1800, 1200));
        SceneManager.addScene("Tab",
                new Scene(FXMLLoader.load(getClass().getResource("views/TabScreen.fxml")), 1800, 1200));
        SceneManager.addScene("MainManager",
                new Scene(FXMLLoader.load(getClass().getResource("views/MainManagerScreen.fxml")), 1800, 1200));
        SceneManager.addScene("EmployeeManager",
                new Scene(FXMLLoader.load(getClass().getResource("views/EmployeeManager.fxml")), 1800, 1200));
        SceneManager.addScene("UserTransaction",
                new Scene(FXMLLoader.load(getClass().getResource("views/UserTransaction.fxml")), 1800, 1200));

        primaryStage.setTitle("GatorPOS");
        primaryStage.setFullScreen(false);
        SceneManager.getInstance().setStage(primaryStage);
        activateScene("Home");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Connection con = ConnectionManager.getConnection();

        launch(args);
    }

    public void activateScene(String name) {
        SceneManager.getInstance().activate(name);
    }
}
