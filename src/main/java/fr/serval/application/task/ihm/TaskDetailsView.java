package fr.serval.application.task.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class TaskDetailsView implements IHMComponentBuilder {
    private final GridPane taskDetailsView;
    private final Label taskDescription;
    private final TreeView<String> commitTree;
    private final TreeItem<String> commitRoot;
    private final Button finishTask;

    private final Project project;

    public TaskDetailsView(Project project) {
        this.taskDetailsView = new GridPane();
        this.taskDescription = new Label();
        this.commitTree = new TreeView<>();
        this.commitRoot = new TreeItem<>();
        this.finishTask = new Button();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.commitTree.setRoot(this.commitRoot);
        this.commitTree.setShowRoot(false);
        this.commitTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.commitRoot.setExpanded(true);

        this.taskDetailsView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.taskDetailsView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 10));
        this.taskDetailsView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 70));
        this.taskDetailsView.getRowConstraints().add(2, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));

        this.taskDetailsView.add(this.taskDescription, 0, 0);
        this.taskDetailsView.add(this.commitTree, 0, 1);
        this.taskDetailsView.add(this.finishTask, 0, 2);

        this.finishTask.setText("Finir la tache");
    }

    public void updateDetails(JSONObject taskJSONObject) {
        this.taskDescription.setText(JSONTools.extractStringFromJSONObject(taskJSONObject, "description"));
        JSONArray taskCommits = APIController.getInstance().getAPITaskController().getAllCommitsForATask(this.project.getId(), JSONTools.extractIntFromJSONObject(taskJSONObject, "id"));
        List<JSONObject> commits = JSONTools.collectJSONArrayChildrenAsArrayList(taskCommits);
        for (JSONObject commit : commits) {
            this.commitRoot.getChildren().add(new TreeItem<>(JSONTools.extractStringFromJSONObject(commit, "name")));
        }
    }

    @Override
    public GridPane getComponent() {
        return this.taskDetailsView;
    }
}
