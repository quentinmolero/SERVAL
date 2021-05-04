package fr.serval.launcher.ihm;

import fr.serval.git.GitAuthController;
import fr.serval.git.GitController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField loginUsername;
    @FXML private TextField loginToken;

    @FXML
    private void handleSignIn() throws Exception {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();

        gitAuthController.login(loginUsername.getText(), loginToken.getText());
        System.out.println(gitAuthController.getSession());
        if(gitAuthController.getSession() != null){
            LauncherMainView.setRoot("LauncherMainView");
            LoginView.closeStage();
        }
    }
}
