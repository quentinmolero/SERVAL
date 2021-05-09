package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class APIProjectController
{
    public JSONArray getUserRepos() throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/users/all", APIAuthController.getSession());
    }

    public JSONObject getProjetCommit(String projet_name) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callGetURLWithBearerToken("projects/" + projet_name + "/commits", APIAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projet_id) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/" + projet_id + "/taskGroups", APIAuthController.getSession());
    }

    public void removeUserFromAProject(String projet_name, int user_id) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_name", projet_name);
        parameters.put("user_id", user_id);
        APIRouter.callDeleteURLWithBearerToken("projects/users", parameters, APIAuthController.getSession());
    }
}
