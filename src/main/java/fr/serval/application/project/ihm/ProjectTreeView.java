package fr.serval.application.project.ihm;

import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectController;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectTreeView implements IHMComponentBuilder {
    private final TreeView<String> treeView;
    private final TreeItem<String> rootNode;

    private final List<ProjectRootNode> projectRootNodeList;

    public ProjectTreeView() {
        this.treeView = new TreeView<>();
        this.rootNode = new TreeItem<>();

        this.projectRootNodeList = new ArrayList<>();

        setupComponent();
    }

    @Override
    public void setupComponent() {
        this.treeView.setRoot(this.rootNode);
        this.treeView.setShowRoot(false);
        this.treeView.getSelectionModel().selectedIndexProperty().addListener(e -> {
            TreeItem<String> selectedNode = this.treeView.getSelectionModel().getSelectedItem();
            List<ProjectRootNode> projectRootNodeList = this.projectRootNodeList.stream().filter(node -> node.getProject().getName().equals(selectedNode.getParent().getValue())).collect(Collectors.toList());
            if (projectRootNodeList.size() > 0) {
                String selectedNodeValue = this.treeView.getSelectionModel().getSelectedItem().getValue();
                ProjectTreeNode projectTreeNode = projectRootNodeList.get(0).findChildNodeFromName(selectedNodeValue);
                ProjectController.getInstance().getProjectCoreView().displayInCoreView((Node) projectTreeNode.getDisplayComponent());
            }
        });

        this.rootNode.setExpanded(true);
    }

    @Override
    public TreeView<String> getComponent() {
        return this.treeView;
    }

    public void setProjectList(List<Project> projectList) {
        this.resetProjectTree();

        for (Project project : projectList) {
            this.insertProjectNode(project);
        }
    }

    public void insertProjectNode(Project project) {
        ProjectRootNode projectNode = new ProjectRootNode();
        projectNode.setProject(project);
        projectNode.setupComponent();
        this.projectRootNodeList.add(projectNode);
        this.rootNode.getChildren().add(projectNode.getComponent());
    }

    public void resetProjectTree() {
        this.rootNode.getChildren().clear();
    }
}
