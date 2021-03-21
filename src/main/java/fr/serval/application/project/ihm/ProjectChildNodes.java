package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class ProjectChildNodes {

    private final Project project;
    private final List<IHMComponentBuilder> childNodesInstance;
    private final List<DefaultMutableTreeNode> childNodes;

    public ProjectChildNodes(Project project) {
        this.project = project;
        this.childNodesInstance = new ArrayList<>();
        this.childNodes = new ArrayList<>();

        setupChildNodes();
    }

    private void setupChildNodes() {
        addChildNode(new ProjectGitNode(this.project));
        addChildNode(new ProjectTaskNode(this.project));
        addChildNode(new ProjectTicketNode(this.project));
    }

    public void addChildNode(IHMComponentBuilder childNodeInstance) {
        childNodeInstance.setupComponent();
        this.childNodes.add((DefaultMutableTreeNode) childNodeInstance.getComponent());
    }

    public List<DefaultMutableTreeNode> getChildNodes() {
        return childNodes;
    }
}
