package fr.serval.launcher.plugin.ihm;

import fr.serval.launcher.plugin.Plugin;
import fr.serval.launcher.plugin.PluginController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PluginList {
    private final JPanel pluginTable;
    private List<Plugin> plugins;
    private BoxLayout pluginLayout;

    public PluginList() {
        this.pluginTable = new JPanel();
        setPluginLayout();
    }

    public JPanel getPluginTable() {
        fetchPluginList();
        clearPluginTable();
        fillPluginTable();
        return this.pluginTable;
    }

    private void fetchPluginList() {
        this.plugins = PluginController.getInstance().getPluginImporter().getPluginList();
    }

    private void clearPluginTable() {
        this.pluginTable.removeAll();
    }

    private void fillPluginTable() {
        this.pluginTable.setLayout(pluginLayout);
        for (Plugin plugin : this.plugins) {
            this.pluginTable.add(formatPluginName(plugin));
        }
    }

    private JPanel formatPluginName(Plugin plugin) {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        JLabel label = new JLabel(plugin.getName(), JLabel.CENTER);
        JCheckBox checkBox = new JCheckBox();

        checkBox.setSelected(plugin.isEnabled());
        checkBox.addActionListener(e -> {
            plugin.setEnabled(checkBox.isEnabled());
        });

        panel.setLayout(layout);
        panel.add(label, BorderLayout.LINE_START);
        panel.add(checkBox, BorderLayout.LINE_END);

        return panel;
    }

    private void setPluginLayout() {
        this.pluginLayout = new BoxLayout(pluginTable, BoxLayout.Y_AXIS);
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}
