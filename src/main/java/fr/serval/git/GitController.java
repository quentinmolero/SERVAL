package fr.serval.git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GitController {
    private final String ACCESS_TOKEN = "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";
    private final String USER_NAME = "quentinmolero";

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

    public void getAllRepositoriesFromUser() throws ParseException, IOException {
        String contentString = getUserRepos();
        JSONArray array = jsonStringToArray(contentString);
        System.out.println(array);
    }

    JSONArray jsonStringToArray(String jsonString) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray)parser.parse(jsonString);
    }

    public String getUserRepos() throws IOException
    {
        URL url = new URL("https://api.github.com/user/repos");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", getAuthHeaderValue());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        return content.toString();
    }

    private String getAuthHeaderValue()
    {
        String auth = this.USER_NAME + ":" + this.ACCESS_TOKEN;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth);
    }
}