package fr.serval.git;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class GitRepoController
{
    public JSONArray getUserRepos() throws IOException, ParseException {
        GitAuthController gitAuthController = GitController.getInstance().getGitAuthController();
        return (JSONArray) RouteController.callGetURLWithBearerToken("http://localhost:3000/projects/users/all", gitAuthController.getSession());
    }
}
