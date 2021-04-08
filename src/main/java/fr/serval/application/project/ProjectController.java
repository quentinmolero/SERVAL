package fr.serval.application.project;

import fr.serval.application.project.ihm.ProjectCoreView;
import fr.serval.application.project.ihm.ProjectTreeView;
import fr.serval.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class ProjectController implements Controller {

    private static ProjectController instance;

    private final ProjectTreeView projectTreeView;
    private final ProjectCoreView projectCoreView;
    private final List<Project> projectList;
    private final String[] names = {"Warsaw", "Minsk", "Kiev", "Riga", "Vilnius", "Tallinn"};

    public ProjectController() {
        this.projectTreeView = new ProjectTreeView();
        this.projectCoreView = new ProjectCoreView();
        this.projectList = new ArrayList<>();

        fillProjectListForDev();
        fillProjectTreeForDev();
    }

    public static ProjectController getInstance() {
        if (instance == null) {
            instance = new ProjectController();
        }

        return instance;
    }

    public ProjectTreeView getProjectTreeView() {
        return projectTreeView;
    }

    public ProjectCoreView getProjectCoreView() {
        return projectCoreView;
    }

    public Project getProjectFromProjectName(String name) {
        Object[] foundObjects = this.projectList.stream().filter(project -> project.getName().equals(name)).toArray();
        if (foundObjects.length > 0) {
            return (Project) foundObjects[0];
        }
        return null;
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
