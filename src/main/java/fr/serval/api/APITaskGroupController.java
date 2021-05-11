package fr.serval.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class APITaskGroupController
{
    private final String ROUTE = "taskGroups/";

    public JSONObject addNewTaskGroupToProject(int project_id, String task_group_name, String task_group_description) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_id", project_id);
        parameters.put("name", task_group_name);
        parameters.put("description", task_group_description);
        return (JSONObject) APIRouter.callPostURLWithBearerToken(ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONObject getTaskGroup(int task_group_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callGetURLWithBearerToken(ROUTE + task_group_id, null, APIAuthController.getSession());
    }
}
