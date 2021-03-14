package fr.serval.application.ihm;

import javax.swing.*;
import java.awt.*;

public class ApplicationMainView {
    private final JFrame mainFrame;

    public ApplicationMainView() {
        this.mainFrame = new JFrame("SERVAL");
        this.mainFrame.setPreferredSize(new Dimension(800, 600));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
}
