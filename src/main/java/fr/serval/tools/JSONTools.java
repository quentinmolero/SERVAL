package fr.serval.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class JSONTools {

    public static JSONArray convertStringToJSONArray(String string) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONArray) jsonParser.parse(string);
    }

    public static int extractIntFromJSONObject(JSONObject jsonObject, String key) {
        return Math.toIntExact((long) jsonObject.get(key));
    }

    public static String extractStringFromJSONObject(JSONObject jsonObject, String key) {
        return jsonObject.get(key).toString();
    }

    public static boolean extractBooleanFromJSONObject(JSONObject jsonObject, String key) {
        return (boolean) jsonObject.get(key);
    }

    public static JSONObject extractJSONObjectFromJSONObject(JSONObject jsonObject, String key) {
        return (JSONObject) jsonObject.get(key);
    }

    public static JSONArray extractJSONArrayFromJSONObject(JSONObject jsonObject, String key) {
        return (JSONArray) jsonObject.get(key);
    }

    public static JSONObject findJSONObjectInJSONArrayWithKeyValue(JSONArray jsonArray, Object key, Object value) {
        for (Object element : jsonArray) {
            JSONObject jsonObject = (JSONObject) element;
            if (doesJSONObjectContainsKeyValue(jsonObject, key, value)) {
                return jsonObject;
            }
        }

        return null;
    }

    public static ArrayList<JSONObject> collectJSONArrayChildrenAsArrayList(JSONArray jsonArray) {
        ArrayList<JSONObject> values = new ArrayList<>();
        for (Object element : jsonArray) {
            values.add((JSONObject) element);
        }
        return values;
    }

    public static boolean doesJSONObjectContainsKeyValue(JSONObject jsonObject, Object key, Object value) {
        return jsonObject.containsKey(key) && jsonObject.containsValue(value);
    }

    public static boolean isJSONObjectNull(JSONObject jsonObject) {
        return jsonObject == null;
    }
}
