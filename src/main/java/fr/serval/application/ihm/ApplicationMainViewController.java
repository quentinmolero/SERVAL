package fr.serval.application.ihm;

import fr.serval.application.menu.MenuController;
import fr.serval.application.project.ProjectController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationMainViewController implements Initializable {

    @FXML
    public MenuBar applicationMenu;

    @FXML
    private GridPane applicationMainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.applicationMenu.getMenus().addAll(MenuController.getInstance().getMenuItemList());
        this.applicationMainPane.add(ProjectController.getInstance().getProjectTreeView().getComponent(), 0, 0);
        this.applicationMainPane.add(ProjectController.getInstance().getProjectCoreView().getComponent(), 1, 0);
    }
}
