package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APITicketController
{
    private final String ROUTE = "tickets/";

    public JSONArray getProjetTickets(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callURL("GET", ROUTE + "projects/"+project_id, null, APIAuthController.getSession());
    }

    public JSONObject addTicketToAProjet(int project_id, String title, String description) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("projectId", project_id);
        parameters.put("title", title);
        parameters.put("description", description);
        return (JSONObject) APIRouter.callURL("POST", ROUTE + "projects", parameters, APIAuthController.getSession());
    }

    public String deleteTicketToAProjet(int ticket_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("ticketId", ticket_id);
        return (String) APIRouter.callURL("DELETE", ROUTE, parameters, APIAuthController.getSession());
    }

    public String addUserToATicket(int ticket_id, int user_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("ticketId", ticket_id);
        parameters.put("userId", user_id);
        return (String) APIRouter.callURL("POST", ROUTE + "users", parameters, APIAuthController.getSession());
    }
}
