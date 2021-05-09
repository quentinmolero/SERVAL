package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class APIProjectController
{
    public JSONObject addProjectToCurrentUser(String repo_name) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("repository", repo_name);
        return (JSONObject) APIRouter.callPostURLWithBearerToken("projects/", parameters, APIAuthController.getSession());
    }

    public JSONArray getCurrentUserProjects() throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/users/all", null, APIAuthController.getSession());
    }

    public JSONArray addUserToProject(String role_name, String project_name, int user_id) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("role_name", role_name);
        parameters.put("project_name", project_name);
        parameters.put("user_id", user_id);
        return (JSONArray) APIRouter.callPostURLWithBearerToken("projects/users", parameters, APIAuthController.getSession());
    }

    public void removeUserFromAProject(String projet_name, int user_id) throws IOException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_name", projet_name);
        parameters.put("user_id", user_id);
        APIRouter.callDeleteURLWithBearerToken("projects/users", parameters, APIAuthController.getSession());
    }

    public JSONObject getProjetCommit(String projet_name) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callGetURLWithBearerToken("projects/" + projet_name + "/commits", null, APIAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projet_id) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/" + projet_id + "/taskGroups", null, APIAuthController.getSession());
    }
}
