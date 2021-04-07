package fr.serval.launcher.ihm;

import fr.serval.GlobalKeys;
import fr.serval.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LauncherMainView extends Application {

    private static Scene launcherScene;
    private static Stage launcherStage;

    @Override
    public void start(Stage stage) throws Exception {
        launcherScene = new Scene(loadFXML("LauncherMainView"));
        launcherStage = stage;
        launcherStage.setScene(launcherScene);
        launcherStage.setTitle(GlobalKeys.SERVAL_NAME_DESC);
        launcherStage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/logo_white_colored_background.png"))));
        launcherStage.setResizable(false);
        launcherStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        launcherScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/launcher/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void closeStage() {
        launcherStage.close();
    }
}
