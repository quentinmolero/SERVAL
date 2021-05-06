package fr.serval.git;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class GitAuthController {
    private String access_token;
    private String session;
    private String user_name;

    public void login(String login, String password) throws IOException, ParseException {
        JSONObject parameters = new JSONObject();
        parameters.put("username", login);
        parameters.put("password", password);
        JSONObject res = (JSONObject) RouteController.callPostURL("http://localhost:3000/auth/login", parameters);
        if (res == null) {
            throw new Error("An error have occurred while calling API");
        }
        access_token = password;
        user_name = login;
        session = res.get("token").toString();
    }

    public void logout() {
        access_token = null;
        user_name = null;
        session = null;
        deleteSaveFile();
    }

    public void writeSaveFile() {
        JSONObject json = new JSONObject();
        SaveFileImporter saveFileImporter = new SaveFileImporter();

        json.put("access_token", access_token);
        json.put("session", session);
        json.put("username", user_name);

        saveFileImporter.setSaveFileContent(json);
    }

    public void deleteSaveFile() {
        SaveFileImporter saveFileImporter = new SaveFileImporter();

        saveFileImporter.deleteSaveFile();
    }

    public void readSaveFile() {
        SaveFileImporter saveFileImporter = new SaveFileImporter();
        JSONObject json = saveFileImporter.getSaveJSON();

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
