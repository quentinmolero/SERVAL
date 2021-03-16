package fr.serval.launcher.plugin;

public class Plugin {
    private final String name;
    private final String file;
    private boolean isEnabled;

    public Plugin(String name, String file, boolean isEnabled) {
        this.name = name;
        this.file = file;
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return this.name;
    }

    public String getFile() {
        return this.file;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
