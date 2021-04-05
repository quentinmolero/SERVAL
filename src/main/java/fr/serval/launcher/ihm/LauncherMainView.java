package fr.serval.launcher.ihm;

import fr.serval.GlobalKeys;
import fr.serval.Main;
import fr.serval.launcher.plugin.ihm.PluginMenu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LauncherMainView extends Application {

    private static Scene launcherScene;
    private PluginMenu pluginMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.pluginMenu = new PluginMenu();
        launcherScene = new Scene(loadFXML("LauncherMainView"));
        primaryStage.setScene(launcherScene);
        primaryStage.setTitle(GlobalKeys.SERVAL_NAME_DESC);
        primaryStage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/logo_white_colored_background.png"))));
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        launcherScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

//
//    private void setStartButton() {
//        this.startButton = new JButton(LauncherKeys.START);
//        this.startButton.addActionListener(e -> {
//            mainFrame.dispose();
//            fr.serval.application.Application.main();
//        });
//    }
//
//    private void setCloseButton() {
//        this.closeButton = new JButton(LauncherKeys.CLOSE);
//        this.closeButton.addActionListener(e -> {
//            mainFrame.dispose();
//            System.exit(0);
//        });
//    }
//
//    private void setManageButton() {
//        this.manageButton = new JButton(LauncherKeys.MANAGE_PLUGINS);
//        this.manageButton.addActionListener(e -> pluginMenu.showWindow());
//    }
}
