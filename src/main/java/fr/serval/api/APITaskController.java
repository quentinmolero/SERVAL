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
        return (JSONObject) APIRouter.callURL("POST", ROUTE, parameters, APIAuthController.getSession());
    }

    public JSONArray getAllTaskForATaskGroup(int projectId, int taskGroupId) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "all?taskGroupId="+taskGroupId+"&projectId="+projectId, null, APIAuthController.getSession());
    }

    public JSONArray getAllCommitsForATask(int projectId, int taskId) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "commits?taskId="+taskId+"&projectId="+projectId, null, APIAuthController.getSession());
    }

    public JSONArray getAllTasksForACommit(int projectId, String commitName) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "commits/task?projectId="+projectId+"&commitName="+commitName.replaceAll(" ", "_").toLowerCase(), null, APIAuthController.getSession());
    }

    public JSONObject updateTaskIsDoneAttribut(int project_id, int task_id, boolean new_is_done) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("projectId", project_id);
        parameters.put("taskId", task_id);
        parameters.put("newIsDone", new_is_done);
        return (JSONObject) APIRouter.callURL("PUT", ROUTE + "isDone", parameters, APIAuthController.getSession());
    }

    public JSONArray getUsersForATask(int project_id, int task_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "users?taskId="+project_id+"&projectId="+task_id, null, APIAuthController.getSession());
    }

    public String addUserToATask(int task_id, String user_name) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("taskId", task_id);
        parameters.put("userName", user_name);
        return (String) APIRouter.callURL("POST", ROUTE + "users", parameters, APIAuthController.getSession());
    }

    public String deleteUserToATask(int task_id, String user_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("taskId", task_id);
        parameters.put("userId", user_id);
        return (String) APIRouter.callURL("DELETE", ROUTE + "users", parameters, APIAuthController.getSession());
    }
}
