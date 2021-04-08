package fr.serval.application.task.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskCategoriesView implements IHMComponentBuilder {
    private final TreeView<String> categoriesTree;
    private final TreeItem<String> categoriesRoot;

    private final String[] categories = {"Login", "Signin", "Disconnect", "DB interactions", "Jardinage"};

    public TaskCategoriesView() {
        this.categoriesTree = new TreeView<>();
        this.categoriesRoot = new TreeItem<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.categoriesTree.setRoot(this.categoriesRoot);
        this.categoriesTree.setShowRoot(false);
        this.categoriesTree.setMaxWidth(100);
        this.categoriesTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.categoriesRoot.setExpanded(true);

        this.updateCategories();
    }

    public void updateCategories() {
        this.categoriesRoot.getChildren().clear();
        for (String c : categories) {
            this.categoriesRoot.getChildren().add(new TreeItem<>(c));
        }
    }

    @Override
    public TreeView<String> getComponent() {
        return this.categoriesTree;
    }
}
