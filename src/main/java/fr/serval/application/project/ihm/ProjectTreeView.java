package fr.serval.application.project.ihm;

import fr.serval.application.git.GitController;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectController;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ProjectTreeView implements IHMComponentBuilder {
    private final TreeView<String> treeView;
    private final TreeItem<String> rootNode;

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
            Project project = ProjectController.getInstance().getProjectFromProjectName(this.treeView.getSelectionModel().getSelectedItem().getValue());
            if (project != null) {
                ProjectController.getInstance().getProjectCoreView().displayInCoreView((new GitController(project)).getComponent());
            }
        });

        this.rootNode.setExpanded(true);
    }

    @Override
    public TreeView<String> getComponent() {
        return this.treeView;
    }

    public void insertProjectNode(Project project) {
        ProjectRootNode projectNode = new ProjectRootNode(project);
        this.rootNode.getChildren().add(projectNode.getComponent());
    }

    public void resetProjectTree() {
        this.rootNode.getChildren().clear();
    }
}
