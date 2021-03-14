package fr.serval.launcher.plugin;

import fr.serval.launcher.plugin.ihm.PluginList;
import fr.serval.launcher.plugin.tools.PluginImporter;

public class PluginController {

    private static PluginController instance;
    private PluginImporter pluginImporter;
    private PluginList pluginList;

    public static PluginController getInstance() {
        return instance;
    }

    public PluginList getPluginList() {
        return pluginList;
    }

    public PluginImporter getPluginImporter() {
        return pluginImporter;
    }
}
