package fr.serval.application.task.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskListView implements IHMComponentBuilder {
    private final TreeView<String> tasksTree;
    private final TreeItem<String> tasksRoot;

    private final String[] tasks = {"Github auth", "connect", "disconnect"};

    public TaskListView() {
        this.tasksTree = new TreeView<>();
        this.tasksRoot = new TreeItem<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.tasksTree.setRoot(this.tasksRoot);
        this.tasksTree.setShowRoot(false);
        this.tasksTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.tasksRoot.setExpanded(true);

        this.updateCategories();
    }

    public void updateCategories() {
        this.tasksRoot.getChildren().clear();
        for (String c : tasks) {
            this.tasksRoot.getChildren().add(new TreeItem<>(c));
        }
    }

    @Override
    public TreeView<String> getComponent() {
        return this.tasksTree;
    }
}
