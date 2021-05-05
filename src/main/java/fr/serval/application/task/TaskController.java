package fr.serval.application.task;

import fr.serval.application.task.ihm.TaskCategoriesView;
import fr.serval.application.task.ihm.TaskDetailsView;
import fr.serval.application.task.ihm.TaskListView;
import fr.serval.controller.Controller;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TaskController implements Controller, IHMComponentBuilder {
    private final GridPane taskMainView;

    private final TaskCategoriesView taskCategoriesView;
    private final TaskListView taskListView;
    private final TaskDetailsView taskDetailsView;

    public TaskController() {
        this.taskMainView = new GridPane();

        this.taskCategoriesView = new TaskCategoriesView();
        this.taskListView = new TaskListView();
        this.taskDetailsView = new TaskDetailsView();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskMainView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 25));
        this.taskMainView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 25));
        this.taskMainView.getColumnConstraints().add(2, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.taskMainView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));

        this.taskMainView.add(this.taskCategoriesView.getComponent(), 0, 0);
        this.taskMainView.add(this.taskListView.getComponent(), 1, 0);
        this.taskMainView.add(this.taskDetailsView.getComponent(), 2, 0);
    }

    @Override
    public GridPane getComponent() {
        return this.taskMainView;
    }
}
