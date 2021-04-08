package fr.serval.application.ihm;

import fr.serval.application.project.ProjectController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationMainViewController implements Initializable {

    @FXML
    private BorderPane applicationMainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.applicationMainPane.setLeft(ProjectController.getInstance().getProjectTreeView().getComponent());
        this.applicationMainPane.setCenter(ProjectController.getInstance().getProjectCoreView().getComponent());
    }
}
