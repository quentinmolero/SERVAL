package fr.serval.application;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.controller.Controller;

import javax.swing.*;

public class ApplicationViewController implements Controller {

    private static ApplicationViewController instance;

    private final ApplicationMainView applicationMainView;

    public ApplicationViewController() {
        applicationMainView = new ApplicationMainView();
    }

    public static ApplicationViewController getInstance() {
        if (instance == null) {
            instance = new ApplicationViewController();
        }
        return instance;
    }

    public ApplicationMainView getApplicationMainView() {
        return applicationMainView;
    }

    public void fillApplicationMainPanel(JComponent component) {
        this.applicationMainView.getMainPanel().removeAll();
        this.applicationMainView.getMainPanel().add(component);
        this.applicationMainView.getMainPanel().revalidate();
        this.applicationMainView.getMainPanel().repaint();
    }
}
