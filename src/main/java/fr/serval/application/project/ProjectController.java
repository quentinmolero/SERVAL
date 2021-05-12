package fr.serval.application.project;

import fr.serval.api.APIController;
import fr.serval.application.project.ihm.ProjectCoreView;
import fr.serval.application.project.ihm.ProjectTreeView;
import fr.serval.controller.Controller;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        JSONArray jsonArray = APIController.getInstance().getAPIProjectController().getCurrentUserProjects();
        List<JSONObject> jsonObjectList = JSONTools.collectJSONArrayChildrenAsArrayList(jsonArray);
        for (JSONObject jsonObject : jsonObjectList) {
            this.projectList.add(Project.buildProjectFromJSONObject(jsonObject));
        }

        this.projectTreeView.setProjectList(this.projectList);
    }

    public List<Project> getProjectList() {
        return projectList;
    }
}
