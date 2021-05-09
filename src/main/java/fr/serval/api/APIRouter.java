package fr.serval.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIRouter
{
    private static final String BASE_URL = "http://localhost:3000/";

    public static Object callGetURL(String urlRoute) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        return readConnection(connection);
    }

    public static Object callGetURLWithBearerToken(String urlRoute, String authValue) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + authValue);

        return readConnection(connection);
    }

    public static Object callPostURL(String urlRoute, JSONObject parameters) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        callParameters(connection, parameters);

        return readConnection(connection);
    }

    public static Object callPostURLWithBearerToken(String urlRoute, JSONObject parameters, String authValue) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + authValue);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        callParameters(connection, parameters);

        return readConnection(connection);
    }

    public static Object callDeleteURL(String urlRoute, JSONObject parameters) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);
        callParameters(connection, parameters);

        return readConnection(connection);
    }

    public static Object callDeleteURLWithBearerToken(String urlRoute, JSONObject parameters, String authValue) throws IOException, ParseException {
        URL url = new URL(BASE_URL + urlRoute);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + authValue);
        callParameters(connection, parameters);

        return readConnection(connection);
    }

    private static void callParameters(HttpURLConnection connection, JSONObject parameters) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write(parameters.toString());
        osw.flush();
        osw.close();
    }

    private static Object readConnection(HttpURLConnection connection) throws IOException, ParseException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;

        StringBuilder response = new StringBuilder();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        JSONParser parser = new JSONParser();
        return parser.parse(response.toString());
    }
}
