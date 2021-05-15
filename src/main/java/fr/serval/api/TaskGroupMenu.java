package fr.serval.api;

import fr.serval.cli.CLIController;

public class TaskGroupMenu {
    public static void main(int projectId){
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1 : Ajouter un groupe de tâches");
            System.out.println("2 : Voir les groupes de tâches");
            System.out.println("3 : Retourner aux autres projets");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    addTaskGroupMenu(projectId);
                    break;
                case 2:
                    selectTaskGroupMenu(projectId);
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void addTaskGroupMenu(int projectId){
        System.out.println("WIP");
    }

    private static void selectTaskGroupMenu(int projectId){
        System.out.println("WIP");
    }
}
