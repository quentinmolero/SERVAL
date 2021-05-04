package fr.serval.launcher.ihm;

import fr.serval.git.GitAuthController;
import fr.serval.git.GitController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField loginUsername;
    @FXML private TextField loginToken;
    @FXML private CheckBox keepConnectCheckbox;

    @FXML
    private void handleSignIn() throws Exception {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();

        gitAuthController.login(loginUsername.getText(), loginToken.getText());
        if(gitAuthController.getSession() != null)
        {
            if(keepConnectCheckbox.isSelected()){
                gitAuthController.writeSaveFile();
            }
            else {
                gitAuthController.deleteSaveFile();
            }
            LauncherMainView.setRoot("LauncherMainView");
            LoginView.closeStage();
        }
    }
}
