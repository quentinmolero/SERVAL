package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APITaskController;
import fr.serval.api.APITaskGroupController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TaskMenu {
    public static void main(int projectId, int taskGroupId){
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1 : Voir les tâches");
            System.out.println("2 : Ajouter une tâches");
            System.out.println("3 : Retourner aux autres groupes de tâches");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectTaskMenu(projectId, taskGroupId);
                    break;
                case 2:
                    addTaskMenu(taskGroupId);
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void selectTaskMenu(int projectId, int taskGroupId){
        int userAnswer;
        APITaskController apiTaskController = APIController.getInstance().getAPITaskController();
        JSONArray taskGroups = apiTaskController.getAllTaskForATaskGroup(projectId, taskGroupId);

        if(taskGroups == null){
            System.out.println("Vous n'avez pas enregistré de tâche");
            return;
        }

        do {
            System.out.println("À quelle tâche souhaitez vous accéder ?");
            for (int i = 0; i < taskGroups.size(); i++)
            {
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) taskGroups.get(i), "name");
                System.out.println((i + 1) + " : " + projectName);
            }
            System.out.println((taskGroups.size() + 1) + " : Retourner à la sélection des projets");
            userAnswer = CLIController.readUserChoice(1, taskGroups.size() + 1);

            if(userAnswer <= taskGroups.size()){
                int taskId = JSONTools.extractIntFromJSONObject((JSONObject) taskGroups.get(userAnswer - 1), "id");
                TaskMenu.tastOptionsMenu(taskId);
            }
        } while(userAnswer != (taskGroups.size() + 1));
    }

    private static void addTaskMenu(int taskGroupId){
        APITaskController apiTaskController = APIController.getInstance().getAPITaskController();
        String taskName;
        String taskDescription;
        do {
            System.out.println("Veuillez saisir le nom de la tâche que vous souhaitez créer : ");
            taskName = CLIController.readStringUserInput();
            System.out.println("Veuillez saisir la description de la tâche que vous souhaitez créer : ");
            taskDescription = CLIController.readStringUserInput();
        } while(apiTaskController.addNewTaskToTaskGroup(taskGroupId, taskName, taskDescription) == null);

        System.out.println("Le groupe de tâche " + taskName + " a bien été ajouté");
    }

    private static void tastOptionsMenu(int taskId){
        System.out.println("WIP"); //TODO: tastOptionsMenu
    }
}