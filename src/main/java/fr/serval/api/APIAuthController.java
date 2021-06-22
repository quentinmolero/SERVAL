package fr.serval.api;

import org.json.simple.JSONObject;

public class APIAuthController {
    private String access_token;
    private String session;
    private String user_name;

    public void login(String login, String password) {
        JSONObject parameters = new JSONObject();
        parameters.put("username", login);
        parameters.put("password", password);
        JSONObject res = (JSONObject) APIRouter.callURL("POST", "auth/login", parameters, null);
        if (res == null) {
            throw new Error("An error have occurred while calling API");
        }
        access_token = password;
        user_name = login;
        session = res.get("token").toString();
    }

    public void logout() {
        APIRouter.callURL("DELETE", "auth/logout", null, session);
        access_token = null;
        user_name = null;
        session = null;
        deleteSaveFile();
    }

    public void writeSaveFile() {
        JSONObject json = new JSONObject();
        LoginSaveImporter loginSaveImporter = new LoginSaveImporter();

        json.put("access_token", access_token);
        json.put("session", session);
        json.put("username", user_name);

        loginSaveImporter.setLoginSaveFileContent(json);
    }

    public void deleteSaveFile() {
        LoginSaveImporter loginSaveImporter = new LoginSaveImporter();

        loginSaveImporter.deleteLoginSaveFile();
    }

    public void readSaveFile() {
        LoginSaveImporter loginSaveImporter = new LoginSaveImporter();
        JSONObject json = loginSaveImporter.getLoginSaveJSON();

        if (json != null && !json.toJSONString().equals("{}")) {
            access_token = json.get("access_token").toString();
            user_name = json.get("username").toString();
            session = json.get("session").toString();
        }
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getSession() {
        return session;
    }

    public String getUserName() {
        return user_name;
    }
}
