package fr.serval.launcher.ihm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    }

    @FXML
    private void handleManagePlugins(ActionEvent actionEvent) {
        actionEvent.consume();
        try {
            LauncherMainView.setRoot("PluginsView");
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
}
