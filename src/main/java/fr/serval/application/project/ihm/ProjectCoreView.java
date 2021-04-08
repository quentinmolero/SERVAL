package fr.serval.application.project.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ProjectCoreView implements IHMComponentBuilder {

    private final BorderPane coreView;

    public ProjectCoreView() {
        this.coreView = new BorderPane();
        setupComponent();
    }

    @Override
    public void setupComponent() {
        this.coreView.setMinHeight(400);
        this.coreView.setMinWidth(400);
    }

    @Override
    public BorderPane getComponent() {
        return this.coreView;
    }

    public void displayInCoreView(Node node) {
        this.coreView.getChildren().clear();
        this.coreView.setCenter(node);
    }
}
