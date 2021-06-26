package fr.serval;

import fr.serval.api.APIController;
import fr.serval.cli.LauncherMenu;
import fr.serval.launcher.Launcher;

public class Main
{
    public static void main(String[] args) {
//        System.out.println(args.length);
        if (args.length > 0 && (args[0].equals("cli") || args[0].equals("-c"))) {
            LauncherMenu.main();
//            APIController.getInstance().getAPIAuthController().readSaveFile();
//            System.out.println(APIController.getInstance().getAPITaskGroupController().getAllTaskGroupForAProject(5));
        } else {
            Launcher.main();
        }
    }
}
