package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

public class ProjectTaskNode implements IHMComponentBuilder {

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
}
