package fr.serval.application.menu.settings;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ApplicationMainView.getMainStage());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Gestion de projet"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.setTitle("Gérer un projet");
            dialog.show();
        });
    }

    @Override
    public MenuItem getComponent() {
        return this.menuItem;
    }
}
