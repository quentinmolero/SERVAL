package fr.serval;

import fr.serval.api.APIController;
import fr.serval.cli.LauncherMenu;
import fr.serval.launcher.Launcher;

public class Main
{
    public static void main(String[] args) {
        if (args.length > 0 && (args[0].equals("cli") || args[0].equals("-c"))) {
            LauncherMenu.main();
        } else {
            Launcher.main();
        }
    }
}
