package fr.serval.application.ihm;

import fr.serval.ihm.IHMFrameBuilder;

import javax.swing.*;
import java.awt.*;

public class ApplicationMainView implements IHMFrameBuilder {
    private final JFrame mainFrame;

    public ApplicationMainView() {
        this.mainFrame = new JFrame("SERVAL");
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void addComponentsInWindow() {

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
}
