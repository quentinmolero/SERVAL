package fr.serval.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APITicketController
{
    private final String ROUTE = "tickets/";

    public JSONArray getProjetTickets(int project_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken(ROUTE + "projects/"+project_id, null, APIAuthController.getSession());
    }

    public JSONObject addTicketToAProjet(int project_id, String title, String description) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("projectId", project_id);
        parameters.put("title", title);
        parameters.put("description", description);
        return (JSONObject) APIRouter.callPostURLWithBearerToken(ROUTE + "projects", parameters, APIAuthController.getSession());
    }

    public void deleteTicketToAProjet(int ticket_id) {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        JSONObject parameters = new JSONObject();
        parameters.put("ticketId", ticket_id);
        APIRouter.callDeleteURLWithBearerToken(ROUTE, parameters, APIAuthController.getSession());
    }
}
