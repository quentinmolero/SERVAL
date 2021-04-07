package fr.serval.application.ihm;

import fr.serval.GlobalKeys;
import fr.serval.Main;
import fr.serval.application.project.ProjectController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ApplicationMainView extends Application {
    private final JFrame mainFrame;
    private BorderLayout borderLayout;
    private JPanel mainPanel;

    private static Scene applicationScene;
    private static Stage applicationStage;

    public ApplicationMainView() {
        this.mainFrame = new JFrame("SERVAL");

        initComponents();
        addComponentsInWindow();
        configureMainFrame();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationScene = new Scene(loadFXML("ApplicationMainView"));
        applicationStage = primaryStage;
        applicationStage.setScene(applicationScene);
        applicationStage.setTitle(GlobalKeys.SERVAL_NAME_DESC);
        applicationStage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/logo_white_colored_background.png"))));
        applicationStage.setResizable(false);
        applicationStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        applicationScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/application/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void launchApplication() {
        ApplicationMainView applicationMainView = new ApplicationMainView();
        try {
            applicationMainView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponents() {
        setBorderLayout();
        setMainPanel();
    }

    public void addComponentsInWindow() {
        this.mainFrame.setLayout(this.borderLayout);
        this.mainFrame.add(ProjectController.getInstance().getProjectTreeView().getComponent(), BorderLayout.WEST);
        this.mainFrame.add(this.mainPanel, BorderLayout.CENTER);
    }

    public void configureMainFrame() {
        this.mainFrame.setPreferredSize(new Dimension(800, 600));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.pack();
    }

    public void showWindow() {
        this.mainFrame.setVisible(true);
    }

    private void setBorderLayout() {
        this.borderLayout = new BorderLayout();
    }

    private void setMainPanel() {
        this.mainPanel = new JPanel();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
