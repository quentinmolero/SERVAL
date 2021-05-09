package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class APIProjectController
{
    public JSONArray getUserRepos() throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/users/all", APIAuthController.getSession());
    }

    public JSONObject getProjetCommit(String projetName) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        return (JSONObject) APIRouter.callGetURLWithBearerToken("projects/" + projetName + "/commits", APIAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projetId) throws IOException, ParseException {
        APIAuthController APIAuthController = APIController.getInstance().getGitAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken("projects/" + projetId + "/taskGroups", APIAuthController.getSession());
    }
}
