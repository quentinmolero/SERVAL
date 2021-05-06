package fr.serval.launcher.ihm;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.git.GitAuthController;
import fr.serval.git.GitController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        if(gitAuthController.getSession() != null){
            githubStatusLabel.setText("Connecté avec " + gitAuthController.getUserName());
            launcherConnexion.setText("Se déconnecter");
        }
    }

    @FXML
    private void handleLaunchApp(ActionEvent actionEvent) {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        actionEvent.consume();
        if(gitAuthController.getSession() != null){
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
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        actionEvent.consume();
        if(gitAuthController.getSession() != null){
            gitAuthController.logout();
            launcherConnexion.setText("Se connecter");
            githubStatusLabel.setText("Non connecté");
        }
        else{
            LoginView loginView = new LoginView();
            loginView.start(new Stage());
        }
    }
}
