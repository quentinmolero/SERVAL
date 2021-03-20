package fr.serval.launcher.plugin;

import fr.serval.controller.Controller;
import fr.serval.launcher.plugin.tools.PluginImporter;

public class PluginController implements Controller {

    private static PluginController instance;

    private final PluginImporter pluginImporter;

    public PluginController() {
        this.pluginImporter = new PluginImporter();
    }

    public static PluginController getInstance() {
        if (instance == null) {
            instance = new PluginController();
        }
        return instance;
    }

    public PluginImporter getPluginImporter() {
        return this.pluginImporter;
    }
}
