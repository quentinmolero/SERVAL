package fr.serval.application.git.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GitCommitTask implements IHMComponentBuilder {
    private final TreeView<String> taskTree;
    private final TreeItem<String> taskRoot;

    private final String[] tasks = {"Create and init project"};

    public GitCommitTask() {
        this.taskTree = new TreeView<>();
        this.taskRoot = new TreeItem<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskTree.setRoot(this.taskRoot);
        this.taskTree.setShowRoot(false);
        this.taskTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.taskRoot.setExpanded(true);

        this.updateTaskList();
    }

    @Override
    public TreeView<String> getComponent() {
        return this.taskTree;
    }

    public void updateTaskList() {
        this.taskRoot.getChildren().clear();
        for (String task : tasks) {
            this.taskRoot.getChildren().add(new TreeItem<>(task));
        }
    }
}
