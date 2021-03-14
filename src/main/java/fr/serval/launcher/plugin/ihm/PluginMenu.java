package fr.serval.launcher.plugin.ihm;

import fr.serval.ihm.IHMFrameBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PluginMenu implements IHMFrameBuilder {
    private final JDialog dialog;
    private JPanel actionPanel;
    private BorderLayout mainLayout;
    private BorderLayout actionLayout;
    private JLabel title;
    private JButton saveButton;
    private JButton closeButton;

    public PluginMenu() {
        this.dialog = new JDialog();

        setActionPanel();

        setMainLayout();
        setActionLayout();

        setTitle();

        setSaveButton();
        setCloseButton();
    }

    @Override
    public void showWindow() {
        addComponentsInWindow();
        configureMainFrame();
    }

    @Override
    public void addComponentsInWindow() {
        this.dialog.setLayout(mainLayout);
        this.dialog.add(title, BorderLayout.NORTH);

        this.actionPanel.setLayout(actionLayout);
        this.actionPanel.add(saveButton, BorderLayout.LINE_START);
        this.actionPanel.add(closeButton, BorderLayout.LINE_END);

        this.dialog.add(actionPanel, BorderLayout.SOUTH);
    }

    @Override
    public void configureMainFrame() {
        this.dialog.setResizable(true);
        this.dialog.setLocationByPlatform(true);
        this.dialog.setModal(true);
        this.dialog.pack();
        this.dialog.setVisible(true);
    }

    private void setActionPanel() {
        this.actionPanel = new JPanel();
    }

    private void setMainLayout() {
        this.mainLayout = new BorderLayout();
    }

    private void setActionLayout() {
        this.actionLayout = new BorderLayout();
    }

    private void setTitle() {
        this.title = new JLabel("Gestion des plugins", JLabel.CENTER);
        this.title.setFont(new Font(new JLabel().getFont().getFontName(), Font.PLAIN, 16));
    }

    private void setSaveButton() {
        this.saveButton = new JButton("Sauvegarder");
    }

    private void setCloseButton() {
        this.closeButton = new JButton("Annuler");
    }
}
