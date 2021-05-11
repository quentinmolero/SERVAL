package fr.serval.application.menu.settings;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.Menu;

public class SettingsMenu implements IHMComponentBuilder {
    private final Menu menu;

    private final ProjectMenuItem projectMenuItem;

    public SettingsMenu() {
        this.menu = new Menu();

        this.projectMenuItem = new ProjectMenuItem();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.menu.textProperty().setValue("Paramètres");

        this.menu.getItems().add(this.projectMenuItem.getComponent());
    }

    @Override
    public Menu getComponent() {
        return this.menu;
    }
}
