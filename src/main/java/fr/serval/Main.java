package fr.serval;

import fr.serval.cli.LauncherMenu;
import fr.serval.launcher.Launcher;

public class Main
{
    public static void main(String[] args) {
        System.out.println(args.length);
        if (args.length > 0 && (args[0].equals("cli") || args[0].equals("-c"))) {
            LauncherMenu.main();
        } else {
            Launcher.main();
        }
    }
}
