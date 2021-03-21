package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectRootNode implements IHMComponentBuilder {

    private final Project project;
    private final ProjectChildNodes projectChildNodes;
    private final DefaultMutableTreeNode defaultMutableTreeNode;

    public ProjectRootNode(Project project) {
        this.project = project;
        this.projectChildNodes = new ProjectChildNodes(this.project);
        this.defaultMutableTreeNode = new DefaultMutableTreeNode(project.getName());
        setupComponent();
    }

    @Override
    public void setupComponent() {
        for (DefaultMutableTreeNode childNode : projectChildNodes.getChildNodes()) {
            this.defaultMutableTreeNode.add(childNode);
        }
    }

    @Override
    public DefaultMutableTreeNode getComponent() {
        return this.defaultMutableTreeNode;
    }
}
