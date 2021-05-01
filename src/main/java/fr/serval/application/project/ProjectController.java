package fr.serval.application.project;

import fr.serval.application.project.ihm.ProjectCoreView;
import fr.serval.application.project.ihm.ProjectTreeView;
import fr.serval.controller.Controller;
import fr.serval.git.GitController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectController implements Controller {

    private static ProjectController instance;

    private final ProjectTreeView projectTreeView;
    private final ProjectCoreView projectCoreView;
    private final List<Project> projectList;

    public ProjectController() {
        this.projectTreeView = new ProjectTreeView();
        this.projectCoreView = new ProjectCoreView();
        this.projectList = new ArrayList<>();

        fillProjectList();
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
        return name != null && this.projectList.stream().noneMatch(project -> project.getName().equals(name));
    }

    private void fillProjectList() {
        try {
            JSONArray jsonArray = JSONTools.convertStringToJSONArray(GitController.getInstance().getUserRepos());
            List<JSONObject> jsonObjectList = JSONTools.collectJSONArrayChildrenAsArrayList(jsonArray);
            for (JSONObject jsonObject : jsonObjectList) {
                String projectName = JSONTools.extractStringFromJSONObject(jsonObject, "name");
                if (canAddProject(projectName)) {
                    this.projectList.add(new Project(projectName));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void fillProjectTreeForDev() {
        this.projectTreeView.resetProjectTree();
        for (Project project : this.projectList) {
            this.projectTreeView.insertProjectNode(project);
        }
    }
}
