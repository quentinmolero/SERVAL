package fr.serval.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LauncherMainController implements Initializable
{
    @FXML
    private Button btnStartApp;
    @FXML
    private Button btnManagePlugins;
    @FXML
    private Button btnExitApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        event.consume();
        if(event.getSource() == btnStartApp)
        {
            System.out.println("Enter");
        }
        if(event.getSource() == btnManagePlugins)
        {
            System.out.println("Manage");
        }
        if(event.getSource() == btnExitApp)
        {
            System.out.println("Exit SERVAL");
            System.exit(0);
        }
    }
}
