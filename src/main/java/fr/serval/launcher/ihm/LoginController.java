package fr.serval.launcher.ihm;

import fr.serval.api.APIAuthController;
import fr.serval.api.APIController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField loginUsername;
    @FXML private TextField loginToken;
    @FXML private CheckBox keepConnectCheckbox;

    @FXML
    private void handleSignIn() throws Exception {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();

        APIAuthController.login(loginUsername.getText(), loginToken.getText());
        if(APIAuthController.getSession() != null)
        {
            if(keepConnectCheckbox.isSelected()){
                APIAuthController.writeSaveFile();
            }
            else {
                APIAuthController.deleteSaveFile();
            }
            LauncherMainView.setRoot("LauncherMainView");
            LoginView.closeStage();
        }
    }
}
