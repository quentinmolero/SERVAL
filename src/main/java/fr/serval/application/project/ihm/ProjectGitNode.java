package fr.serval.application.project.ihm;

import fr.serval.application.git.GitController;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.controller.ProjectTreeNode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;

public class ProjectGitNode implements ProjectTreeNode {

    private Project project;
    private TreeItem<String> gitNode;

    public ProjectGitNode(Project project) {
        this.project = project;
    }

    @Override
    public void setupComponent() {
        this.gitNode = new TreeItem<>(ProjectKeys.GIT_NODE_NAME);
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.gitNode;
    }

    @Override
    public GridPane getDisplayComponent() {
        return (new GitController(this.project)).getComponent();
    }
}
