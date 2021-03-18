package fr.serval.application;

import fr.serval.application.ihm.ApplicationMainView;

public class Application {

    private static ApplicationMainView applicationMainView;

    public static void main() {
        applicationMainView = new ApplicationMainView();
        applicationMainView.showWindow();
    }
}
