package fr.serval.application.menu.settings;

import fr.serval.application.menu.settings.ihm.AddProjectDialog;
import fr.serval.application.menu.settings.ihm.ManageProjectDialog;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class ProjectMenuItem implements IHMComponentBuilder {
    private final MenuItem addProject;
    private final MenuItem manageProject;

    private final List<MenuItem> menuItemList;

    public ProjectMenuItem() {
        this.addProject = new MenuItem();
        this.manageProject = new MenuItem();

        this.menuItemList = new ArrayList<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.addProject.textProperty().setValue("Ajouter un projet");
        this.addProject.setOnAction(event -> {
            final AddProjectDialog addProjectDialog = new AddProjectDialog();
            addProjectDialog.getComponent().show();
        });
        this.manageProject.textProperty().setValue("Gérer un projet");
        this.manageProject.setOnAction(event -> {
            final ManageProjectDialog manageProjectDialog = new ManageProjectDialog();
            manageProjectDialog.getComponent().show();
        });

        this.menuItemList.add(this.addProject);
        this.menuItemList.add(this.manageProject);
    }

    @Override
    public List<MenuItem> getComponent() {
        return this.menuItemList;
    }
}
