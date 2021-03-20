package fr.serval.application.projects.ihm;

import fr.serval.application.projects.Project;
import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectRootNode implements IHMComponentBuilder {

    private final Project project;
    private final DefaultMutableTreeNode defaultMutableTreeNode;

    public ProjectRootNode(Project project) {
        this.project = project;
        this.defaultMutableTreeNode = new DefaultMutableTreeNode(project.getName());
    }

    @Override
    public void setupComponent() {

    }

    @Override
    public DefaultMutableTreeNode getComponent() {
        return this.defaultMutableTreeNode;
    }
}
