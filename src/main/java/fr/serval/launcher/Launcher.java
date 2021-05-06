package fr.serval.launcher;

import fr.serval.git.GitController;
import fr.serval.launcher.ihm.LauncherMainView;
import javafx.application.Application;

public class Launcher {

    public static void main() {
        try {
            GitController.getInstance().getGitAuthController().readSaveFile();
            Application.launch(LauncherMainView.class, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
