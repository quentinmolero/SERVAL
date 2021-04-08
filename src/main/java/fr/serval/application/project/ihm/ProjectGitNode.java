package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

public class ProjectGitNode implements IHMComponentBuilder {

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
}
