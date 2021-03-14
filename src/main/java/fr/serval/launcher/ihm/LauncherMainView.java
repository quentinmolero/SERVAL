package fr.serval.launcher.ihm;

import fr.serval.application.Application;

import javax.swing.*;
import java.awt.*;

public class LauncherMainView {

    private final JFrame mainFrame;
    private JPanel actionPanel;
    private BorderLayout mainLayout;
    private BorderLayout actionLayout;
    private JLabel title;
    private JLabel description;
    private JButton startButton;
    private JButton closeButton;
    private JButton manageButton;

    public LauncherMainView() {
        this.mainFrame = new JFrame("SERVAL Launcher");

        setActionFrame();

        setMainLayout();
        setActionLayout();

        setTitle();
        setDescription();

        setStartButton();
        setCloseButton();
        setManageButton();
    }

    public void showWindow() {
        addComponentsInWindow();
        configureMainFrame();
    }

    private void addComponentsInWindow() {
        this.mainFrame.setLayout(mainLayout);
        this.mainFrame.add(title, BorderLayout.NORTH);
        this.mainFrame.add(description, BorderLayout.CENTER);

        this.actionPanel.setLayout(actionLayout);
        this.actionPanel.add(startButton, BorderLayout.LINE_START);
        this.actionPanel.add(manageButton, BorderLayout.CENTER);
        this.actionPanel.add(closeButton, BorderLayout.LINE_END);

        this.mainFrame.add(actionPanel, BorderLayout.SOUTH);
    }

    private void configureMainFrame() {
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private void setMainLayout() {
        this.mainLayout = new BorderLayout();
    }

    private void setActionFrame() {
        this.actionPanel = new JPanel();
    }

    private void setActionLayout() {
        this.actionLayout = new BorderLayout();
    }

    private void setTitle() {
        this.title = new JLabel("SERVAL");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
        this.title.setFont(new Font(new JLabel().getFont().getFontName(), Font.PLAIN, 24));
    }

    private void setDescription() {
        this.description = new JLabel("Suivi Et Revu de Version ALlégé");
        this.description.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setStartButton() {
        this.startButton = new JButton("Démarrer");
        this.startButton.addActionListener(e -> {
            mainFrame.dispose();
            Application.main();
        });
    }

    private void setCloseButton() {
        this.closeButton = new JButton("Fermer");
        this.closeButton.addActionListener(e -> {
            mainFrame.dispose();
            System.exit(0);
        });
    }

    private void setManageButton() {
        this.manageButton = new JButton("Gérer les plugins");
    }
}
