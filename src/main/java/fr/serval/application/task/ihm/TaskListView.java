package fr.serval.application.task.ihm;

import fr.serval.api.APIController;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class TaskListView implements IHMComponentBuilder {
    private final TreeView<String> tasksTree;
    private final TreeItem<String> tasksRoot;

    private final HashMap<String, JSONObject> taskHashMap;

    private final TaskDetailsView taskDetailsView;

    public TaskListView(TaskDetailsView taskDetailsView) {
        this.tasksTree = new TreeView<>();
        this.tasksRoot = new TreeItem<>();

        this.taskHashMap = new HashMap<>();

        this.taskDetailsView = taskDetailsView;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.tasksTree.setRoot(this.tasksRoot);
        this.tasksTree.setShowRoot(false);
        this.tasksTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.tasksRoot.setExpanded(true);

        this.tasksTree.getSelectionModel().selectedIndexProperty().addListener(e -> {
            TreeItem<String> selectedNode = this.tasksTree.getSelectionModel().getSelectedItem();
            JSONObject selectedTask = this.taskHashMap.get(selectedNode.getValue());
            this.taskDetailsView.updateDetails(selectedTask);
        });
    }

    public void updateTasks(int projectId, int taskCategoriesId) {
        this.taskHashMap.clear();
        this.tasksRoot.getChildren().clear();
        JSONArray groupTasks = APIController.getInstance().getAPITaskController().getAllTaskForATaskGroup(projectId, taskCategoriesId);
        List<JSONObject> tasks = JSONTools.collectJSONArrayChildrenAsArrayList(groupTasks);
        for (JSONObject task : tasks) {
            String taskName = JSONTools.extractStringFromJSONObject(task, "name");

            this.taskHashMap.put(taskName, task);
            this.tasksRoot.getChildren().add(new TreeItem<>(taskName));
        }
    }

    @Override
    public TreeView<String> getComponent() {
        return this.tasksTree;
    }
}
