package fr.serval.application.task.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class TaskCategoriesView implements IHMComponentBuilder {
    private final TreeView<String> categoriesTree;
    private final TreeItem<String> categoriesRoot;

    private final HashMap<String, Integer> taskCategoriesHashMap;

    private final TaskListView taskListView;
    private final Project project;

    public TaskCategoriesView(TaskListView taskListView, Project project) {
        this.categoriesTree = new TreeView<>();
        this.categoriesRoot = new TreeItem<>();

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

        this.updateCategories();
    }

    public void updateCategories() {
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
    public TreeView<String> getComponent() {
        return this.categoriesTree;
    }
}
