package fr.serval.launcher;

import fr.serval.launcher.ihm.LauncherMainView;

import javax.swing.*;

public class Launcher {

    private static LauncherMainView launcherMainView;

    public static void main() {
        launcherMainView = new LauncherMainView();
        SwingUtilities.invokeLater(() -> launcherMainView.showWindow());
    }
}
