package fr.serval.launcher.ihm;

import fr.serval.application.ihm.ApplicationMainView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherMainController implements Initializable
{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleLaunchApp(ActionEvent actionEvent) {
        actionEvent.consume();
        LauncherMainView.closeStage();
        ApplicationMainView.launchApplication();
    }

    @FXML
    private void handleManagePlugins(ActionEvent actionEvent) {
        actionEvent.consume();
        try {
            LauncherMainView.setRoot("plugin/PluginsView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClose(ActionEvent actionEvent) {
        actionEvent.consume();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleConnexion(ActionEvent actionEvent) throws Exception {
        actionEvent.consume();
        LoginView loginView = new LoginView();
        loginView.start(new Stage());
    }
}
