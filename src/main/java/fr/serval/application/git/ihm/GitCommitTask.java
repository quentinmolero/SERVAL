package fr.serval.application.git.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GitCommitTask implements IHMComponentBuilder {
    private final TreeView<String> taskTree;
    private final TreeItem<String> taskRoot;

    private final Project project;

    public GitCommitTask(Project project) {
        this.taskTree = new TreeView<>();
        this.taskRoot = new TreeItem<>();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskTree.setRoot(this.taskRoot);
        this.taskTree.setShowRoot(false);
        this.taskTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.taskRoot.setExpanded(true);
    }

    @Override
    public TreeView<String> getComponent() {
        return this.taskTree;
    }

    public void updateTaskList(String commitName) {
        this.taskRoot.getChildren().clear();
        JSONArray tasksJSONArray = APIController.getInstance().getAPITaskController().getAllTasksForACommit(this.project.getId(), commitName);
        for (JSONObject taskJSONArray : JSONTools.collectJSONArrayChildrenAsArrayList(tasksJSONArray)) {
            this.taskRoot.getChildren().add(new TreeItem<>(JSONTools.extractStringFromJSONObject(taskJSONArray, "name")));
        }
    }
}
