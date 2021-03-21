package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectKeys;
import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectTicketNode implements IHMComponentBuilder {

    private Project project;
    private DefaultMutableTreeNode gitNode;

    public ProjectTicketNode(Project project) {
        this.project = project;
    }

    @Override
    public void setupComponent() {
        this.gitNode = new DefaultMutableTreeNode(ProjectKeys.TICKET_NODE_NAME);
    }

    @Override
    public DefaultMutableTreeNode getComponent() {
        return this.gitNode;
    }
}
