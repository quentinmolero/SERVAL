package fr.serval.application.project;

import fr.serval.tools.JSONTools;
import org.json.simple.JSONObject;

public class Project {
    private final int id;
    private final String name;
    private final String url;

    public static Project buildProjectFromJSONObject(JSONObject jsonObject) {
        int id = JSONTools.extractIntFromJSONObject(jsonObject, "id");
        String name = JSONTools.extractStringFromJSONObject(jsonObject, "name");
        String url = JSONTools.extractStringFromJSONObject(jsonObject, "url_html");

        return new Project(id, name, url);
    }

    public Project(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
