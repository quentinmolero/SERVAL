package fr.serval.launcher.ihm;

import fr.serval.git.GitController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML private TextField loginUsername;
    @FXML private TextField loginToken;

    @FXML
    private void handleSignIn(ActionEvent actionEvent) throws IOException {
        GitController.getInstance().login(loginUsername.getText(), loginToken.getText());
        System.out.println(GitController.getInstance().getSession());
        if(GitController.getInstance().getSession() != null){
            LoginView.closeStage();
        }
    }
}
