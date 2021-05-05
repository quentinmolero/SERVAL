package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.application.task.TaskController;
import fr.serval.controller.ProjectTreeNode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;

public class ProjectTaskNode implements ProjectTreeNode {

    private Project project;
    private TreeItem<String> gitNode;

    public ProjectTaskNode(Project project) {
        this.project = project;
    }

    @Override
    public void setupComponent() {
        this.gitNode = new TreeItem<>(ProjectKeys.TASK_NODE_NAME);
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.gitNode;
    }

    @Override
    public GridPane getDisplayComponent() {
        return (new TaskController()).getComponent();
    }
}
