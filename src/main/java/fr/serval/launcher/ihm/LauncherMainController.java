package fr.serval.launcher.ihm;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.api.APIAuthController;
import fr.serval.api.APIController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherMainController implements Initializable
{
    @FXML public Label errorLabel;
    @FXML public Label githubStatusLabel;
    @FXML public Button launcherConnexion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        if(APIAuthController.getSession() != null){
            githubStatusLabel.setText("Connecté avec " + APIAuthController.getUserName());
            launcherConnexion.setText("Se déconnecter");
        }
    }

    @FXML
    private void handleLaunchApp(ActionEvent actionEvent) {
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        actionEvent.consume();
        if(APIAuthController.getSession() != null){
            LauncherMainView.closeStage();
            ApplicationMainView.launchApplication();
        }
        else{
            errorLabel.setWrapText(true);
            errorLabel.setText("Vous devez être connecté pour pouvoir démarrer SERVAL");
        }
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
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        actionEvent.consume();
        if(APIAuthController.getSession() != null){
            APIAuthController.logout();
            launcherConnexion.setText("Se connecter");
            githubStatusLabel.setText("Non connecté");
        }
        else{
            LoginView loginView = new LoginView();
            loginView.start(new Stage());
        }
    }
}
