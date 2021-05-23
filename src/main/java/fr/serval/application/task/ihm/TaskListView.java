package fr.serval.application.task.ihm;

import fr.serval.api.APIController;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class TaskListView implements IHMComponentBuilder {
    private final GridPane taskListBox;
    private final TreeView<String> tasksTree;
    private final TreeItem<String> tasksRoot;
    private final Button addTask;

    private final HashMap<String, JSONObject> taskHashMap;

    private final TaskDetailsView taskDetailsView;

    private int projectId;
    private int taskCategoriesId;

    public TaskListView(TaskDetailsView taskDetailsView) {
        this.taskListBox = new GridPane();
        this.tasksTree = new TreeView<>();
        this.tasksRoot = new TreeItem<>();
        this.addTask = new Button();

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

        this.addTask.setText("Ajouter une tache");
        this.addTask.setDisable(true);
        this.addTask.setOnAction(event -> {
            final NewTaskDialog newTaskDialog = new NewTaskDialog(this, this.projectId, this.taskCategoriesId);
            newTaskDialog.getComponent().show();
        });

        this.taskListBox.add(this.tasksTree, 0, 0);
        this.taskListBox.add(this.addTask, 0, 1);
        this.taskListBox.getColumnConstraints().add(GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.taskListBox.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 80));
        this.taskListBox.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
    }

    public void updateTasks(int projectId, int taskCategoriesId) {
        this.taskHashMap.clear();
        this.tasksRoot.getChildren().clear();
        this.projectId = projectId;
        this.taskCategoriesId = taskCategoriesId;
        JSONArray groupTasks = APIController.getInstance().getAPITaskController().getAllTaskForATaskGroup(this.projectId, this.taskCategoriesId);
        List<JSONObject> tasks = JSONTools.collectJSONArrayChildrenAsArrayList(groupTasks);
        for (JSONObject task : tasks) {
            String taskName = JSONTools.extractStringFromJSONObject(task, "name");

            this.taskHashMap.put(taskName, task);
            this.tasksRoot.getChildren().add(new TreeItem<>(taskName));
        }
        this.addTask.setDisable(false);
    }

    @Override
    public GridPane getComponent() {
        return this.taskListBox;
    }
}
