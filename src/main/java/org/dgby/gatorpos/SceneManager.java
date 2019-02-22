package org.dgby.gatorpos;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance = null;
    private static HashMap<String, Scene> sceneMap = new HashMap<>();
    private Stage mainStage;

    private SceneManager() {
    }

    protected static void addScene(String name, Scene scene) {
        sceneMap.put(name, scene);
    }

    public void activate(String name) {
        if (mainStage != null)
            mainStage.setScene(sceneMap.get(name));
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