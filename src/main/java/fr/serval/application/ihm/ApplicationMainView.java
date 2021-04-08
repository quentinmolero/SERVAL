package fr.serval.application.ihm;

import fr.serval.GlobalKeys;
import fr.serval.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMainView extends Application {
    private static Scene applicationScene;
    private static Stage applicationStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationScene = new Scene(loadFXML("ApplicationMainView"));
        applicationStage = primaryStage;
        applicationStage.setScene(applicationScene);
        applicationStage.setOnCloseRequest(event -> {
            ApplicationMainView.closeStage();
        });
        applicationStage.setTitle(GlobalKeys.SERVAL_NAME_DESC);
        applicationStage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/logo_white_colored_background.png"))));
        applicationStage.setResizable(false);
        applicationStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        applicationScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/application/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void launchApplication() {
        ApplicationMainView applicationMainView = new ApplicationMainView();
        try {
            applicationMainView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeStage() {
        applicationStage.close();
        Platform.exit();
        System.exit(0);
    }

}
