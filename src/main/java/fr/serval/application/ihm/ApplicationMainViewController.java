package fr.serval.application.ihm;

import fr.serval.application.project.ProjectController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationMainViewController implements Initializable {

    @FXML
    private VBox applicationMainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.applicationMainPane.getChildren().add(ProjectController.getInstance().getProjectTreeView().getComponent());
    }
}
