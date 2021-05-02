package fr.serval.application.plugin;

import fr.serval.controller.NodeController;
import fr.serval.controller.PluginController;

import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private static PluginLoader instance;
    private List<PluginController> pluginController;
    private List<NodeController> pluginNodeController;

    public PluginLoader() {
        pluginNodeController = new ArrayList<>();
    }

    public static PluginLoader getInstance() {
        if (instance == null) {
            instance = new PluginLoader();
        }

        return instance;
    }

    public void setPluginController(List<PluginController> pluginController) {
        this.pluginController = pluginController;
    }

    public void loadAllController() {
        for (PluginController controller : this.pluginController) {
            pluginNodeController.addAll(controller.getNodeControllerList());
        }
    }

    public List<NodeController> getPluginNodeController() {
        return this.pluginNodeController;
    }
}
