package fr.serval.controller;

import fr.serval.launcher.ihm.LauncherMainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PluginsViewController implements Initializable
{
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        event.consume();
        if(event.getSource() == btnSave)
        {
            System.out.println("TODO: Sauvegarder les paramètres");
        }
        LauncherMainView.setRoot("LauncherMainView");
    }
}
