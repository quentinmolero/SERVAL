package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class APIProjectController
{
    private final String ROUTE = "projects/";

    public JSONObject addProjectToCurrentUser(String repo_name) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("repository", repo_name);
        return (JSONObject) APIRouter.callPostURLWithBearerToken(ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONArray getCurrentUserProjects() {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken(ROUTE + "users/all", null, APIAuthController.getSession());
    }

    public JSONObject addUserToProject(String role_name, int project_id, int user_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("role_name", role_name);
        parameters.put("project_id", project_id);
        parameters.put("user_id", user_id);
        return (JSONObject) APIRouter.callPostURLWithBearerToken(ROUTE + "users", parameters, APIAuthController.getSession());
    }

    public void removeUserFromAProject(int project_id, int user_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_id", project_id);
        parameters.put("user_id", user_id);
        APIRouter.callDeleteURLWithBearerToken(ROUTE + "users", parameters, APIAuthController.getSession());
    }

    public JSONObject getProjetCommit(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callGetURLWithBearerToken(ROUTE + project_id + "/commits", null, APIAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projet_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken(ROUTE + projet_id + "/taskGroups", null, APIAuthController.getSession());
    }
}
