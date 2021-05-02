package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class ProjectChildNodes {

    private final Project project;
    private final List<ProjectTreeNode> childNodesInstance;
    private final List<TreeItem<String>> childNodes;

    public ProjectChildNodes(Project project) {
        this.project = project;
        this.childNodesInstance = new ArrayList<>();
        this.childNodes = new ArrayList<>();
    }

    public void addChildNode(ProjectTreeNode childNodeInstance) {
        childNodeInstance.setupComponent();
        this.childNodesInstance.add(childNodeInstance);
        this.childNodes.add((TreeItem<String>) childNodeInstance.getComponent());
    }

    public List<TreeItem<String>> getChildNodes() {
        return this.childNodes;
    }

    public List<ProjectTreeNode> getChildNodesInstance() {
        return this.childNodesInstance;
    }
}
