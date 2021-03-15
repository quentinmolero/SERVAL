package fr.serval.launcher;

import fr.serval.launcher.ihm.LauncherMainView;
import fr.serval.launcher.plugin.PluginController;
import fr.serval.launcher.plugin.tools.PluginImporter;

import javax.swing.*;

public class Launcher {

    private static LauncherMainView launcherMainView;

    public static void main() {
        launcherMainView = new LauncherMainView();
        PluginController.getInstance().getPluginImporter();
        SwingUtilities.invokeLater(() -> launcherMainView.showWindow());
    }
}
