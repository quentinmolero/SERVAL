package fr.serval.application.project.ihm;

import fr.serval.application.git.GitController;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.application.ticket.TicketController;
import fr.serval.controller.ProjectTreeNode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;

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
    public GridPane getDisplayComponent() {
        return (new TicketController()).getComponent();
    }
}
