package fr.serval.cli;

import fr.serval.api.APIAuthController;
import fr.serval.api.APIController;

public class LoginMenu {
    public static void main(){
        System.out.println("=================");

        APIAuthController apiAuthController = APIController.getInstance().getAPIAuthController();

        do {
            if(apiAuthController.getUserName() != null){
                System.out.println("Les identifiants que vous avez saisis ne sont pas corrects");
                System.out.println("=================");
            }
            System.out.print("Saisissez votre nom d'utilisateur : ");
            String login = CLIController.readStringUserInput();

            System.out.print("Saisissez votre token : ");
            String token = CLIController.readStringUserInput();

            apiAuthController.login(login, token);
        }
        while (apiAuthController.getSession() == null);

        System.out.println("=================");

        System.out.println("Vous êtes désormait connecté en tant que " + apiAuthController.getUserName());

        System.out.println("Souhaitez vous resté connecté ?");
        System.out.println("1 : Oui");
        System.out.println("2 : Non");
        if(CLIController.readUserChoice(1, 2) == 1){
            apiAuthController.writeSaveFile();
        }
        else{
            apiAuthController.deleteSaveFile();
        }
    }
}
