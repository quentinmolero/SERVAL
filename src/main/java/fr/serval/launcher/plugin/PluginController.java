package fr.serval.launcher.plugin;

import fr.serval.launcher.plugin.tools.PluginImporter;

public class PluginController {

    private static PluginController instance;

    private PluginImporter pluginImporter;

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
