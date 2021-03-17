package fr.serval.launcher.plugin.ihm;

import fr.serval.ihm.IHMFrameBuilder;
import fr.serval.launcher.LauncherKeys;
import fr.serval.launcher.plugin.PluginController;

import javax.swing.*;
import java.awt.*;

public class PluginMenu implements IHMFrameBuilder {
    private final PluginList pluginList;

    private final JDialog dialog;
    private JPanel actionPanel;
    private BorderLayout mainLayout;
    private BorderLayout actionLayout;
    private JLabel title;
    private JButton saveButton;
    private JButton closeButton;

    public PluginMenu() {
        this.pluginList = new PluginList();
        this.dialog = new JDialog();

        initComponents();
        addComponentsInWindow();
        configureMainFrame();
    }

    @Override
    public void initComponents() {
        setActionPanel();

        setMainLayout();
        setActionLayout();

        setTitle();

        setSaveButton();
        setCloseButton();
    }

    @Override
    public void addComponentsInWindow() {
        this.dialog.setLayout(mainLayout);
        this.dialog.add(title, BorderLayout.NORTH);
        this.dialog.add(pluginList.getPluginTable(), BorderLayout.CENTER);

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
        this.dialog.setTitle(LauncherKeys.MANAGER_NAME);
        this.dialog.pack();
    }

    @Override
    public void showWindow() {
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
        this.title = new JLabel(LauncherKeys.MANAGE_PLUGINS, JLabel.CENTER);
        this.title.setFont(new Font(new JLabel().getFont().getFontName(), Font.PLAIN, 16));
    }

    private void setSaveButton() {
        this.saveButton = new JButton(LauncherKeys.SAVE);
        this.saveButton.addActionListener(e -> {
            PluginController.getInstance().getPluginImporter().setPluginFileContent(this.pluginList.getPlugins());
            this.dialog.dispose();
        });
    }

    private void setCloseButton() {
        this.closeButton = new JButton(LauncherKeys.CANCEL);
        this.closeButton.addActionListener(e -> this.dialog.dispose());
    }
}
