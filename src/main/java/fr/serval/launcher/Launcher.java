package fr.serval.launcher;

import fr.serval.launcher.ihm.LauncherMainView;

import javax.swing.*;

public class Launcher {

    private static LauncherMainView launcherMainView;

    public static void main() {
        System.out.println(System.getProperty(LauncherKeys.USER_HOME_DIR));
        launcherMainView = new LauncherMainView();

        SwingUtilities.invokeLater(() -> launcherMainView.showWindow());
    }
}
