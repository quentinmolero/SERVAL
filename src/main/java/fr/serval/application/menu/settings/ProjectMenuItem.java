package fr.serval.application.menu.settings;

import fr.serval.application.menu.settings.ihm.ManageProjectDialog;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.MenuItem;


public class ProjectMenuItem implements IHMComponentBuilder {
    private final MenuItem menuItem;

    public ProjectMenuItem() {
        this.menuItem = new MenuItem();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.menuItem.textProperty().setValue("Gérer un projet");
        this.menuItem.setOnAction(event -> {
            final ManageProjectDialog manageProjectDialog = new ManageProjectDialog();
            manageProjectDialog.getComponent().show();
        });
    }

    @Override
    public MenuItem getComponent() {
        return this.menuItem;
    }
}
