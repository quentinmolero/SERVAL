package fr.serval.application.task;

import fr.serval.application.task.ihm.TaskCategoriesView;
import fr.serval.application.task.ihm.TaskDetailsView;
import fr.serval.application.task.ihm.TaskListView;
import fr.serval.controller.Controller;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.layout.HBox;

public class TaskController implements Controller, IHMComponentBuilder {
    private final HBox taskMainView;

    private final TaskCategoriesView taskCategoriesView;
    private final TaskListView taskListView;
    private final TaskDetailsView taskDetailsView;

    public TaskController() {
        this.taskMainView = new HBox();

        this.taskCategoriesView = new TaskCategoriesView();
        this.taskListView = new TaskListView();
        this.taskDetailsView = new TaskDetailsView();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskMainView.getChildren().add(this.taskCategoriesView.getComponent());
        this.taskMainView.getChildren().add(this.taskListView.getComponent());
        this.taskMainView.getChildren().add(this.taskDetailsView.getComponent());
    }

    @Override
    public HBox getComponent() {
        return this.taskMainView;
    }
}
