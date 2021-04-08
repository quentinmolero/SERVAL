package fr.serval.application.git.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GitCommitDetails implements IHMComponentBuilder {
    private final HBox gitDetailsView;

    public GitCommitDetails() {
        this.gitDetailsView = new HBox();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.gitDetailsView.setPrefHeight(200);
        this.gitDetailsView.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.gitDetailsView.getChildren().add(new Label("Complete report about the commit"));
    }

    @Override
    public HBox getComponent() {
        return this.gitDetailsView;
    }
}
