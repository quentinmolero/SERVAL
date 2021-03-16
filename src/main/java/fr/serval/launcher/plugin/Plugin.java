package fr.serval.launcher.plugin;

public class Plugin {
    private final String name;
    private final String file;
    private final boolean isEnabled;

    public Plugin(String name, String file, boolean isEnabled) {
        this.name = name;
        this.file = file;
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
