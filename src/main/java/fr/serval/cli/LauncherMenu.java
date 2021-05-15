package fr.serval.cli;

import fr.serval.api.APIAuthController;
import fr.serval.api.APIController;

public class LauncherMenu {
    public static void main(){
        APIController apiController = APIController.getInstance();
        APIAuthController apiAuthController = apiController.getAPIAuthController();

        apiAuthController.readSaveFile();
        if(apiAuthController.getSession() == null){
            System.out.println("Vous devez d'abord être connecté pour pouvoir utiliser Serval");
            LoginMenu.main();
        }
        else{
            System.out.println("Vous êtes déjà connecté en tant que " + apiAuthController.getUserName());
            System.out.println("Voulez vous :");
            System.out.println("1 : Lancer Serval");
            System.out.println("2 : Vous déconnecter");
            if(CLIController.readUserChoice(1, 2) == 2){
                apiAuthController.logout();
                LoginMenu.main();
            }
        }

        AppMenu.main();
    }
}
