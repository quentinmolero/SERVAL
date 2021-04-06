package fr.serval.launcher.plugin.ihm;

import fr.serval.launcher.ihm.LauncherMainView;
import fr.serval.launcher.plugin.PluginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PluginsViewController implements Initializable
{
    private PluginList pluginList;
    @FXML
    private VBox pluginsVBoxPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pluginList = new PluginList();
        this.pluginsVBoxPane.getChildren().addAll(this.pluginList.getPluginList());
    }

    @FXML
    private void handleSavePluginList(ActionEvent event) throws IOException {
        PluginController.getInstance().getPluginImporter().setPluginFileContent(this.pluginList.getPlugins());
        this.handleClosePluginList(event);
    }

    @FXML
    private void handleClosePluginList(ActionEvent event) throws IOException {
        event.consume();
        LauncherMainView.setRoot("LauncherMainView");
    }
}
