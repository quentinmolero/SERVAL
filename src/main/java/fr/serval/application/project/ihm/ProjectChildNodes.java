package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class ProjectChildNodes {

    private final Project project;
    private final List<IHMComponentBuilder> childNodesInstance;
    private final List<TreeItem<String>> childNodes;

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
        this.childNodes.add((TreeItem<String>) childNodeInstance.getComponent());
    }

    public List<TreeItem<String>> getChildNodes() {
        return childNodes;
    }
}
