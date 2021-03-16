package fr.serval.launcher.plugin.ihm;

import fr.serval.launcher.LauncherKeys;
import fr.serval.launcher.plugin.Plugin;
import fr.serval.launcher.plugin.PluginController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
        if (this.plugins.isEmpty()) {
            this.pluginTable.add(noPluginFound());
        } else {
            for (Plugin plugin : this.plugins) {
                this.pluginTable.add(formatPluginName(plugin));
            }
        }
    }

    private JPanel formatPluginName(Plugin plugin) {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        JLabel label = new JLabel(plugin.getName(), JLabel.LEFT);
        JCheckBox checkBox = new JCheckBox();

        checkBox.setSelected(plugin.isEnabled());
        checkBox.addActionListener(e -> plugin.setEnabled(checkBox.isEnabled()));

        panel.setLayout(layout);
        panel.add(pluginFileState(plugin), BorderLayout.LINE_START);
        panel.add(label, BorderLayout.CENTER);
        panel.add(checkBox, BorderLayout.LINE_END);

        return panel;
    }

    private JPanel pluginFileState(Plugin plugin) {
        JPanel state = new JPanel();
        File jarFile = new File(PluginController.getInstance().getPluginImporter().getServalHomeDir() + File.separator + plugin.getFile());

        state.setPreferredSize(new Dimension(10, 10));
        state.setMaximumSize(new Dimension(10, 10));

        if (jarFile.exists()) {
            state.setBackground(Color.GREEN);
            state.setToolTipText(LauncherKeys.PLUGIN_STATE_NOMINAL);
        } else {
            state.setBackground(Color.RED);
            state.setToolTipText(LauncherKeys.PLUGIN_STATE_NOT_FOUND);
        }

        return state;
    }

    private JPanel noPluginFound() {
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        JLabel label = new JLabel(LauncherKeys.ERROR_NO_PLUGIN_FOUND, JLabel.CENTER);

        panel.setLayout(borderLayout);
        panel.add(label);

        return panel;
    }

    private void setPluginLayout() {
        this.pluginLayout = new BoxLayout(pluginTable, BoxLayout.Y_AXIS);
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}
