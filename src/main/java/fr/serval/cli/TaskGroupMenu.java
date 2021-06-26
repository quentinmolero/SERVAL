package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APITaskGroupController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TaskGroupMenu {
    public static void main(JSONObject project){
        System.out.println("=================");

        int userAnswer;

        do {
            System.out.println("Que souhaitez vous faire sur " + JSONTools.extractStringFromJSONObject(project, "name") + " ?");
            System.out.println("1 : Voir les groupes de tâches");
            System.out.println("2 : Ajouter un groupe de tâches");
            System.out.println("3 : Retourner aux autres projets");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectTaskGroupMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
                case 2:
                    addTaskGroupMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void selectTaskGroupMenu(int projectId){
        System.out.println("=================");
        int userAnswer;
        APITaskGroupController apiTaskGroupController = APIController.getInstance().getAPITaskGroupController();
        JSONArray taskGroups = apiTaskGroupController.getAllTaskGroupForAProject(projectId);

        if(taskGroups.size() == 0){
            System.out.println("Vous n'avez pas enregistré de groupe de tâches");
            return;
        }

        do {
            System.out.println("À quel groupe de tâches souhaitez vous accéder ?");
            for (int i = 0; i < taskGroups.size(); i++)
            {
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) taskGroups.get(i), "name");
                System.out.println((i + 1) + " : " + projectName);
            }
            System.out.println((taskGroups.size() + 1) + " : Retourner à la sélection des projets");
            userAnswer = CLIController.readUserChoice(1, taskGroups.size() + 1);

            if(userAnswer <= taskGroups.size()){
                TaskMenu.main(projectId, (JSONObject) taskGroups.get(userAnswer - 1));
            }
        } while(userAnswer != (taskGroups.size() + 1));
    }

    private static void addTaskGroupMenu(int projectId){
        System.out.println("=================");
        APITaskGroupController apiTaskGroupController = APIController.getInstance().getAPITaskGroupController();
        String taskGroupName;
        String taskGroupDescription;
        do {
            System.out.println("Veuillez saisir le nom du groupe de tâches que vous souhaitez créer : ");
            taskGroupName = CLIController.readStringUserInput();
            System.out.println("Veuillez saisir la description du groupe de tâches que vous souhaitez créer : ");
            taskGroupDescription = CLIController.readStringUserInput();
        } while(apiTaskGroupController.addNewTaskGroupToProject(projectId, taskGroupName, taskGroupDescription) == null);

        System.out.println("Le groupe de tâche " + taskGroupName + " a bien été ajouté");
    }
}
