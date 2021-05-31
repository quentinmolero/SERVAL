package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APITaskController
{
    private final String ROUTE = "tasks/";

    public JSONObject addNewTaskToTaskGroup(int task_group_id, String task_name, String task_description) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("task_group_id", task_group_id);
        parameters.put("name", task_name);
        parameters.put("description", task_description);
        return (JSONObject) APIRouter.callPostURLWithBearerToken(ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONArray getAllTaskForATaskGroup(int projectId, int taskGroupId) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken(ROUTE + "all?taskGroupId="+taskGroupId+"&projectId="+projectId, null, APIAuthController.getSession());
    }
}
