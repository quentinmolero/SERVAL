package fr.serval.application.projects;

import fr.serval.application.projects.ihm.ProjectTreeView;
import fr.serval.application.task.TaskController;
import fr.serval.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class ProjectsController implements Controller {

    private static ProjectsController instance;

    private final ProjectTreeView projectTreeView;
    private final List<Project> projectList;
    private final String[] names = {"Warsaw", "Minsk", "Kiev", "Riga", "Vilnius", "Tallinn"};

    public ProjectsController() {
        this.projectTreeView = new ProjectTreeView();
        this.projectList = new ArrayList<>();

        fillProjectListForDev();
        fillProjectTreeForDev();

        TaskController.getInstance().setupProjectsViews(this.projectList);
    }

    public static ProjectsController getInstance() {
        if (instance == null) {
            instance = new ProjectsController();
        }

        return instance;
    }

    public ProjectTreeView getProjectTreeView() {
        return projectTreeView;
    }

    public Project getProjectFromProjectName(String name) {
        return (Project) this.projectList.stream().filter(project -> project.getName().equals(name)).toArray()[0];
    }

    public boolean canAddProject(String name) {
        return this.projectList.stream().noneMatch(project -> project.getName().equals(name));
    }

    private void fillProjectListForDev() {
        for (String name : names) {
            if (canAddProject(name)) {
                this.projectList.add(new Project(name));
            }
        }
    }

    private void fillProjectTreeForDev() {
        this.projectTreeView.resetProjectTree();
        for (Project project : this.projectList) {
            this.projectTreeView.insertProjectNode(project);
        }
    }
}
