package fr.serval.application.task;

import fr.serval.application.projects.Project;
import fr.serval.application.task.ihm.TaskMainView;
import fr.serval.controller.Controller;

import java.util.HashMap;
import java.util.List;

public class TaskController implements Controller {

    private static TaskController instance;
    private final HashMap<Project, TaskMainView> projectViews;

    public TaskController() {
        this.projectViews = new HashMap<>();
    }

    public static TaskController getInstance() {
        if (instance == null) {
            instance = new TaskController();
        }
        return instance;
    }

    public void setupProjectsViews(List<Project> projectList) {
        for (Project project : projectList) {
            this.projectViews.put(project, new TaskMainView(project));
        }
    }

    public Object getProjectTaskView(Project project) {
        return this.projectViews.get(project).getComponent();
    }
}
