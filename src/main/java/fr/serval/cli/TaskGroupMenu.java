package fr.serval.cli;

import fr.serval.cli.CLIController;

public class TaskGroupMenu {
    public static void main(int projectId){
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1 : Voir les groupes de tâches");
            System.out.println("2 : Ajouter un groupe de tâches");
            System.out.println("3 : Retourner aux autres projets");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectTaskGroupMenu(projectId);
                    break;
                case 2:
                    addTaskGroupMenu(projectId);
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void selectTaskGroupMenu(int projectId){
        System.out.println("WIP"); // TODO: selectTaskGroupMenu
    }

    private static void addTaskGroupMenu(int projectId){
        System.out.println("WIP"); // TODO: addTaskGroupMenu
    }
}
