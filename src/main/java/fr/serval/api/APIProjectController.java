package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APIProjectController
{
    private final String ROUTE = "projects/";

    public JSONObject addProjectToCurrentUser(String repo_name) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("repository", repo_name);
        return (JSONObject) APIRouter.callURL("POST", ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONArray getCurrentUserProjects() {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "users/all", null, APIAuthController.getSession());
    }

    public JSONArray getUsersFromAProject(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "users?projectId="+project_id, null, APIAuthController.getSession());
    }

    public JSONObject addUserToProject(String role_name, int project_id, String user_name) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("role_name", role_name);
        parameters.put("project_id", project_id);
        parameters.put("user_name", user_name);
        return (JSONObject) APIRouter.callURL("POST", ROUTE + "users", parameters, APIAuthController.getSession());
    }

    public void removeUserFromAProject(int project_id, String user_name) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_id", project_id);
        parameters.put("user_name", user_name);
        APIRouter.callURL("DELETE", ROUTE + "users", parameters, APIAuthController.getSession());
    }

    public JSONObject getProjetCommit(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callURL("GET", ROUTE + project_id + "/commits", null, APIAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projet_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + projet_id + "/taskGroups", null, APIAuthController.getSession());
    }
}
