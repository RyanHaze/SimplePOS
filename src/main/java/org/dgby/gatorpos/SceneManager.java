package org.dgby.gatorpos;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance = null;
    private static HashMap<String, Parent> parentMap = new HashMap<>();
    private static Scene scene;
    private static Stage mainStage;

    private SceneManager() {
    }

    // add the passed parameters to hashmap
    protected static void addParent(String name, Parent parent) {
        parentMap.put(name, parent);
    }

    // create the initial and ONLY scene
    public void createInitialScene(String name) {
        if (mainStage != null) {
            scene = new Scene(parentMap.get("Home"));
            mainStage.setScene(scene);
        }
    }

    // change the root of the ONLY scene
    public void changeParent(String name) {
        scene.setRoot(parentMap.get(name));
    }

    public void setStage(Stage stage) {
        mainStage = stage;
    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }
}