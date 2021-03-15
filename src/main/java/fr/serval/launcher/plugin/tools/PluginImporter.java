package fr.serval.launcher.plugin.tools;

import fr.serval.launcher.LauncherKeys;

import javax.swing.*;
import java.io.File;

public class PluginImporter {
    private final File userHomeDir;
    private final File servalHomeDir;

    public PluginImporter() {
        this.userHomeDir = new File(System.getProperty(LauncherKeys.USER_HOME_DIR));
        this.servalHomeDir = new File(userHomeDir + String.valueOf(File.separator) + "SERVAL");

        if (!isUserHomeDirPresent()) {
            JOptionPane.showMessageDialog(null, LauncherKeys.ERROR_NO_HOME_DIR, LauncherKeys.ERROR_PLUGIN_IMPORTER, JOptionPane.ERROR_MESSAGE);
        } else if (!isServalDirPresent() && !createServalDir()) {
            JOptionPane.showMessageDialog(null, LauncherKeys.ERROR_CAN_NOT_CREATE_SERVAL_DIR + this.servalHomeDir.getAbsolutePath(), LauncherKeys.ERROR_PLUGIN_IMPORTER, JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isUserHomeDirPresent() {
        return this.userHomeDir.exists();
    }

    private boolean isServalDirPresent() {
        return this.servalHomeDir.exists();
    }

    private boolean createServalDir() {
        return this.servalHomeDir.mkdir();
    }
}
