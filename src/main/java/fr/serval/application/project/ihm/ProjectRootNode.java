package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

public class ProjectRootNode implements IHMComponentBuilder {

    private final Project project;
    private final ProjectChildNodes projectChildNodes;
    private final TreeItem<String> projectRootNode;

    public ProjectRootNode(Project project) {
        this.project = project;
        this.projectChildNodes = new ProjectChildNodes(this.project);
        this.projectRootNode = new TreeItem<>(project.getName());
        setupComponent();
    }

    @Override
    public void setupComponent() {
        projectChildNodes.addChildNode(new ProjectGitNode(project));
        projectChildNodes.addChildNode(new ProjectTicketNode(project));
        projectChildNodes.addChildNode(new ProjectTaskNode(project));

        for (TreeItem<String> childNode : projectChildNodes.getChildNodes()) {
            this.projectRootNode.getChildren().add(childNode);
        }
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.projectRootNode;
    }
}
