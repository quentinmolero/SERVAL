package fr.serval.application.project;

import fr.serval.tools.JSONTools;
import org.json.simple.JSONObject;

public class Project {
    private final String name;
    private final String url;

    public static Project buildProjectFromJSONObject(JSONObject jsonObject) {
        String name = JSONTools.extractStringFromJSONObject(jsonObject, "name");
        String url = JSONTools.extractStringFromJSONObject(jsonObject, "url_html");

        return new Project(name, url);
    }

    public Project(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
