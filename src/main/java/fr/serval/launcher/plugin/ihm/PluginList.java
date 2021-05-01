package fr.serval.launcher.plugin.ihm;

import fr.serval.launcher.LauncherKeys;
import fr.serval.launcher.plugin.Plugin;
import fr.serval.launcher.plugin.PluginController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PluginList {
    private final List<HBox> pluginList;
    private List<Plugin> plugins;

    public PluginList() {
        this.pluginList = new ArrayList<>();
    }

    public List<HBox> getPluginList() {
        fetchPluginList();
        clearPluginTable();
        fillPluginTable();
        return this.pluginList;
    }

    private void fetchPluginList() {
        this.plugins = PluginController.getInstance().getPluginImporter().getPluginList();
    }

    private void clearPluginTable() {
        this.pluginList.clear();
    }

    private void fillPluginTable() {
        if (this.plugins.isEmpty()) {
            this.pluginList.add(noPluginFound());
        } else {
            for (Plugin plugin : this.plugins) {
                this.pluginList.add(formatPluginName(plugin));
            }
        }
    }

    private HBox formatPluginName(Plugin plugin) {
        HBox box = new HBox();
        Label label = new Label(plugin.getName());
        CheckBox checkBox = new CheckBox();

        box.setPrefWidth(600);

        label.setFont(new Font(18));
        label.setTextAlignment(TextAlignment.LEFT);

        checkBox.setFont(new Font(18));
        checkBox.setSelected(plugin.isEnabled());
        checkBox.setOnAction(e -> plugin.setEnabled(checkBox.isSelected()));

        box.getChildren().add(pluginFileState(plugin));
        box.getChildren().add(checkBox);
        box.getChildren().add(label);

        return box;
    }

    private Rectangle pluginFileState(Plugin plugin) {
        Rectangle state = new Rectangle();
        File jarFile = plugin.getFile();

        state.setHeight(18);
        state.setWidth(18);

        if (jarFile.exists()) {
            state.setFill(Color.GREEN);
        } else {
            state.setFill(Color.RED);
        }

        return state;
    }

    private HBox noPluginFound() {
        HBox box = new HBox();
        Label label = new Label(LauncherKeys.ERROR_NO_PLUGIN_FOUND);
        label.setTextAlignment(TextAlignment.CENTER);

        box.getChildren().add(label);

        return box;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}
