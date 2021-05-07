package fr.serval.git;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class GitRepoController
{
    public JSONArray getUserRepos() throws IOException, ParseException {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        return (JSONArray) RouteController.callGetURLWithBearerToken("projects/users/all", gitAuthController.getSession());
    }

    public JSONObject getProjetCommit(String projetName) throws IOException, ParseException {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        return (JSONObject) RouteController.callGetURLWithBearerToken("projects/" + projetName + "/commits", gitAuthController.getSession());
    }

    public JSONArray getProjetTaskGroup(int projetId) throws IOException, ParseException {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        return (JSONArray) RouteController.callGetURLWithBearerToken("projects/" + projetId + "/taskGroups", gitAuthController.getSession());
    }
}
