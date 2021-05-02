package fr.serval.application.project.ihm;

import fr.serval.application.git.GitController;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectController;
import fr.serval.application.task.TaskController;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ProjectTreeView implements IHMComponentBuilder {
    private final TreeView<String> treeView;
    private final TreeItem<String> rootNode;
    private ProjectRootNode projectNode;

    public ProjectTreeView() {
        this.treeView = new TreeView<>();
        this.rootNode = new TreeItem<>();

        setupComponent();
    }

    @Override
    public void setupComponent() {
        this.treeView.setRoot(this.rootNode);
        this.treeView.setShowRoot(false);
        this.treeView.setMaxWidth(200);
        this.treeView.getSelectionModel().selectedIndexProperty().addListener(e -> {
            String selectedNodeValue = this.treeView.getSelectionModel().getSelectedItem().getValue();
            ProjectTreeNode projectTreeNode = this.projectNode.findChildNodeFromName(selectedNodeValue);
            if (projectTreeNode != null) {
                ProjectController.getInstance().getProjectCoreView().displayInCoreView((Node) projectTreeNode.getDisplayComponent());
            } else {
                ProjectController.getInstance().getProjectCoreView().displayInCoreView(null);
            }
        });

        this.rootNode.setExpanded(true);
    }

    @Override
    public TreeView<String> getComponent() {
        return this.treeView;
    }

    public void insertProjectNode(Project project) {
        this.projectNode = new ProjectRootNode();
        this.projectNode.setProject(project);
        this.projectNode.setupComponent();
        this.rootNode.getChildren().add(this.projectNode.getComponent());
    }

    public void resetProjectTree() {
        this.rootNode.getChildren().clear();
    }
}
