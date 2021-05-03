package fr.serval.git;

import org.json.simple.JSONObject;

import java.io.IOException;

public class GitAuthController {
    private String access_token;
    private String session;
    private String user_name;

    private static GitAuthController instance;

    public static GitAuthController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new GitAuthController();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static GitAuthController resetInstance() {
        deleteInstance();
        return getInstance();
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public void login(String login, String password) throws IOException {
        JSONObject parameters = new JSONObject();
        parameters.put("username", login);
        parameters.put("password", password);
        JSONObject res = RouteController.callPostURL("http://localhost:3000/auth/login", parameters);
        if(res == null){
            throw new Error("An error have occurred while calling API");
        }
        access_token = password;
        user_name = login;
        session = res.get("token").toString();
    }

    public String getSession() {
        return session;
    }
}
