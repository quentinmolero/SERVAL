package fr.serval.launcher.ihm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void handleSignIn(ActionEvent actionEvent) {
        actionEvent.consume();
        //Platform.exit();
    }
}
