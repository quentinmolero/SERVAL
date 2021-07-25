package fr.serval.application.task.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class TaskCategoriesView implements IHMComponentBuilder {
    private final GridPane taskCategoriesBox;
    private final TreeView<String> categoriesTree;
    private final TreeItem<String> categoriesRoot;
    private final Button addCategoriesTask;

    private final HashMap<String, Integer> taskCategoriesHashMap;

    private final TaskListView taskListView;
    private final Project project;

    public TaskCategoriesView(TaskListView taskListView, Project project) {
        this.taskCategoriesBox = new GridPane();
        this.categoriesTree = new TreeView<>();
        this.categoriesRoot = new TreeItem<>();
        this.addCategoriesTask = new Button();

        this.taskCategoriesHashMap = new HashMap<>();

        this.taskListView = taskListView;
        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.categoriesTree.setRoot(this.categoriesRoot);
        this.categoriesTree.setShowRoot(false);
        this.categoriesTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.categoriesRoot.setExpanded(true);


        this.categoriesTree.getSelectionModel().selectedIndexProperty().addListener(e -> {
            TreeItem<String> selectedNode = this.categoriesTree.getSelectionModel().getSelectedItem();
            int selectedCategories = this.taskCategoriesHashMap.get(selectedNode.getValue());
            this.taskListView.updateTasks(this.project.getId(), selectedCategories);
        });

        this.addCategoriesTask.setText("Ajouter une categorie");
        this.addCategoriesTask.setOnAction(event -> {
            final NewCategoriesDialog newCategoriesDialog = new NewCategoriesDialog(this, this.project.getId());
            newCategoriesDialog.getComponent().show();
        });

        this.taskCategoriesBox.add(this.categoriesTree, 0, 0);
        this.taskCategoriesBox.add(this.addCategoriesTask, 0, 1);
        this.taskCategoriesBox.getColumnConstraints().add(GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.taskCategoriesBox.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 80));
        this.taskCategoriesBox.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));

        this.updateCategories();
    }

    public void updateCategories() {
        this.taskCategoriesHashMap.clear();
        JSONObject projectTaskGroup = (JSONObject) APIController.getInstance().getAPIProjectController().getProjetTaskGroup(this.project.getId()).get(0);
        List<JSONObject> taskCategories = JSONTools.collectJSONArrayChildrenAsArrayList(JSONTools.extractJSONArrayFromJSONObject(projectTaskGroup, "Task_Groups"));
        for (JSONObject categories : taskCategories) {
            String categoriesName = JSONTools.extractStringFromJSONObject(categories, "name");
            int categoriesId = JSONTools.extractIntFromJSONObject(categories, "id");

            this.taskCategoriesHashMap.put(categoriesName, categoriesId);
            this.categoriesRoot.getChildren().add(new TreeItem<>(categoriesName));
        }
    }

    @Override
    public GridPane getComponent() {
        return this.taskCategoriesBox;
    }
}
