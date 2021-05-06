package fr.serval.git;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RouteController {
    public static JSONObject callPostURL(String urlString, JSONObject parameters) throws IOException {
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
}
