package fr.serval.launcher.plugin;

import java.io.File;

public class Plugin {
    private final String name;
    private final String fileName;
    private final File file;
    private boolean isEnabled;

    public Plugin(String name, String fileName, boolean isEnabled) {
        this.name = name;
        this.fileName = fileName;
        this.file = new File(PluginController.getInstance().getPluginImporter().getServalHomeDir() + File.separator + this.fileName);
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public File getFile() {
        return this.file;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
