package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APITaskGroupController
{
    private final String ROUTE = "taskGroups/";

    public JSONObject addNewTaskGroupToProject(int project_id, String task_group_name, String task_group_description) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("project_id", project_id);
        parameters.put("name", task_group_name);
        parameters.put("description", task_group_description);
        return (JSONObject) APIRouter.callURL("POST", ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONArray getAllTaskGroupForAProject(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "all?projectId="+project_id, null, APIAuthController.getSession());
    }

    public JSONObject getTaskGroup(int task_group_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONObject) APIRouter.callURL("GET", ROUTE + task_group_id, null, APIAuthController.getSession());
    }
}
