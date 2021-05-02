package fr.serval.application.project.ihm;

import fr.serval.application.plugin.PluginLoader;
import fr.serval.application.project.Project;
import fr.serval.controller.NodeController;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;

import java.util.List;

public class ProjectRootNode implements IHMComponentBuilder, NodeController {

    private Project project;
    private ProjectChildNodes projectChildNodes;
    private TreeItem<String> projectRootNode;

    @Override
    public void setupComponent() {
        this.projectChildNodes = new ProjectChildNodes(this.project);
        this.projectRootNode = new TreeItem<>(project.getName());

        projectChildNodes.addChildNode(new ProjectGitNode(project));
        projectChildNodes.addChildNode(new ProjectTicketNode(project));
        projectChildNodes.addChildNode(new ProjectTaskNode(project));

        for (NodeController nodeController : PluginLoader.getInstance().getPluginNodeController()) {
            nodeController.setProject(project);
            for (ProjectTreeNode node : nodeController.getNodeList()) {
                projectChildNodes.addChildNode(node);
            }
        }

        for (TreeItem<String> childNode : projectChildNodes.getChildNodes()) {
            this.projectRootNode.getChildren().add(childNode);
        }
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.projectRootNode;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public List<ProjectTreeNode> getNodeList() {
        return null;
    }

    public ProjectTreeNode findChildNodeFromName(String name) {
        for (ProjectTreeNode childNodeInstance : this.projectChildNodes.getChildNodesInstance()) {
            if (name.equals(((TreeItem<String>) childNodeInstance.getComponent()).getValue())) {
                return childNodeInstance;
            }
        }
        return null;
    }
}
