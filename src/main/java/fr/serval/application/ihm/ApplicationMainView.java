package fr.serval.application.ihm;

import fr.serval.application.projects.ProjectsController;
import fr.serval.ihm.IHMFrameBuilder;

import javax.swing.*;
import java.awt.*;

public class ApplicationMainView implements IHMFrameBuilder {
    private final JFrame mainFrame;
    private BorderLayout borderLayout;
    private JPanel mainPanel;

    public ApplicationMainView() {
        this.mainFrame = new JFrame("SERVAL");

        initComponents();
        addComponentsInWindow();
        configureMainFrame();
    }

    @Override
    public void initComponents() {
        setBorderLayout();
        setMainPanel();
    }

    @Override
    public void addComponentsInWindow() {
        this.mainFrame.setLayout(this.borderLayout);
        this.mainFrame.add(ProjectsController.getInstance().getProjectTreeView().getComponent(), BorderLayout.WEST);
        this.mainFrame.add(this.mainPanel, BorderLayout.EAST);
    }

    @Override
    public void configureMainFrame() {
        this.mainFrame.setPreferredSize(new Dimension(800, 600));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.pack();
    }

    @Override
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
