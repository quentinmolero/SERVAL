package fr.serval.launcher.plugin.ihm;

import javax.swing.*;

public class PluginList {
    private final JPanel pluginTable;
    private final String[] names = {"Amiens", "Reims", "Brest", "Toulon", "Toulouse", "Strasbourg", "Vichy", "Nantes"};
    private BoxLayout pluginLayout;

    public PluginList() {
        this.pluginTable = new JPanel();

        setPluginLayout();
    }

    public JPanel getPluginTable() {
        fillPluginTable();
        return this.pluginTable;
    }

    private void fillPluginTable() {
        this.pluginTable.setLayout(pluginLayout);
        for (String name : names) {
            JLabel label = new JLabel(name, JLabel.CENTER);
            this.pluginTable.add(label);
        }
    }

    private void setPluginLayout() {
        this.pluginLayout = new BoxLayout(pluginTable, BoxLayout.Y_AXIS);
    }
}
