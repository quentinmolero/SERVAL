package fr.serval.git;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GitController {
    private String access_token;
    private String session;
    private String user_name;

    private static GitController instance;

    public static GitController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new GitController();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static GitController resetInstance() {
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
        JSONObject res = callPostURL("http://localhost:3000/auth/login", parameters);
        if(res == null){
            throw new Error("An error have occurred while calling API");
        }
        access_token = password;
        user_name = login;
        session = res.get("token").toString();
    }

    public JSONObject callPostURL(String urlString, JSONObject parameters) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write(parameters.toString());
        osw.flush();
        osw.close();

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(response.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSession() {
        return session;
    }
}
