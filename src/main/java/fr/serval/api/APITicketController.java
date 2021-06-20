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
}
