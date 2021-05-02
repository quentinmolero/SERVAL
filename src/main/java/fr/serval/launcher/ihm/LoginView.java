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

public class LoginView extends Application {
    private static Scene loginScene;
    private static Stage loginStage;

    @Override
    public void start(Stage stage) throws Exception {
        loginScene = new Scene(loadFXML("LoginView"));
        loginStage = stage;
        loginStage.setScene(loginScene);
        loginStage.setTitle(GlobalKeys.SERVAL_LOGIN);
        loginStage.setResizable(false);
        loginStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        loginScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/launcher/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void closeStage() {
        loginStage.close();
    }
}
