package fr.serval.api;

import org.json.simple.JSONArray;

public class APIRoleController
{
    private final String ROUTE = "roles/";

    public JSONArray getAllRoles() {
        APIAuthController APIAuthController = APIController.getInstance().getAPIAuthController();
        return (JSONArray) APIRouter.callGetURLWithBearerToken(ROUTE + "all", null, APIAuthController.getSession());
    }
}
