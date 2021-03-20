package fr.serval.application.projects;

import fr.serval.application.projects.ihm.ProjectTreeView;
import fr.serval.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class ProjectsController implements Controller {

    private static ProjectsController instance;

    private final ProjectTreeView projectTreeView;
    private final List<Project> projectList;

    public ProjectsController() {
        this.projectTreeView = new ProjectTreeView();
        this.projectList = new ArrayList<>();

        fillProjectListForDev();
        fillProjectTreeForDev();
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

    private void fillProjectListForDev() {
        this.projectList.add(new Project("Warsaw"));
        this.projectList.add(new Project("Minsk"));
        this.projectList.add(new Project("Kiev"));
        this.projectList.add(new Project("Riga"));
        this.projectList.add(new Project("Vilnius"));
        this.projectList.add(new Project("Tallinn"));
    }

    private void fillProjectTreeForDev() {
        this.projectTreeView.resetProjectTree();
        for (Project project : this.projectList) {
            this.projectTreeView.insertProjectNode(project);
        }
    }
}
