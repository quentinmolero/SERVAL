package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class ProjectTicketNode implements ProjectTreeNode {

    private Project project;
    private TreeItem<String> gitNode;

    public ProjectTicketNode(Project project) {
        this.project = project;
    }

    @Override
    public void setupComponent() {
        this.gitNode = new TreeItem<>(ProjectKeys.TICKET_NODE_NAME);
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.gitNode;
    }

    @Override
    public Label getDisplayComponent() {
        return new Label(ProjectKeys.TICKET_NODE_NAME);
    }
}
