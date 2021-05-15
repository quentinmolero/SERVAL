package fr.serval.cli;

public class AppMenu {
    public static void main(){
        int userAnswer;
        System.out.println("=================");
        System.out.println("Bienvenue sur Serval CLI !");

        do {
            System.out.println("1 : Accéder à un projet");
            System.out.println("2 : Ajouter un nouveau projet");
            System.out.println("3 : Quitter Serval");

            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    ProjectMenu.main();
                    break;
                case 2:
                    break;
            }
        }
        while(userAnswer != 3);
    }
}
