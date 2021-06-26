package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APITaskController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TaskMenu {
    public static void main(int projectId, JSONObject taskGroup){
        System.out.println("=================");
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire avec " + JSONTools.extractStringFromJSONObject(taskGroup, "name") + " ?");
            System.out.println("1 : Voir les tâches");
            System.out.println("2 : Ajouter une tâches");
            System.out.println("3 : Retourner aux autres groupes de tâches");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectTaskMenu(projectId, JSONTools.extractIntFromJSONObject(taskGroup, "id"));
                    break;
                case 2:
                    addTaskMenu(JSONTools.extractIntFromJSONObject(taskGroup, "id"));
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void selectTaskMenu(int projectId, int taskGroupId){
        System.out.println("=================");
        int userAnswer;
        APITaskController apiTaskController = APIController.getInstance().getAPITaskController();
        JSONArray taskGroups = apiTaskController.getAllTaskForATaskGroup(projectId, taskGroupId);

        if(taskGroups.size() == 0){
            System.out.println("Vous n'avez pas enregistré de tâche");
            return;
        }

        do {
            System.out.println("À quelle tâche souhaitez vous accéder ?");
            for (int i = 0; i < taskGroups.size(); i++)
            {
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) taskGroups.get(i), "name");
                String is_done = "[ ]";
                if(JSONTools.extractStringFromJSONObject((JSONObject) taskGroups.get(i), "is_done").equals("true")){
                    is_done = "[x]";
                }
                System.out.println((i + 1) + " : " + is_done + " " + projectName);
            }
            System.out.println((taskGroups.size() + 1) + " : Retourner à la sélection des groupes de tâche");
            userAnswer = CLIController.readUserChoice(1, taskGroups.size() + 1);

            if(userAnswer <= taskGroups.size()){
                TaskMenu.taskOptionsMenu(projectId, (JSONObject) taskGroups.get(userAnswer - 1));
            }
        } while(userAnswer != (taskGroups.size() + 1));
    }

    private static void addTaskMenu(int taskGroupId){
        System.out.println("=================");
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

    private static void taskOptionsMenu(int projectId, JSONObject task){
        System.out.println("=================");
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire avec " + JSONTools.extractStringFromJSONObject(task, "name") + " ? ID = " + JSONTools.extractIntFromJSONObject(task, "id"));
            System.out.println("1 : Gérer les membres");
            System.out.println("2 : Voir les commits associés");
            if(JSONTools.extractStringFromJSONObject(task, "is_done").equals("false")){
                System.out.println("3 : Indiquer que la tâche est terminée");
            }
            else {
                System.out.println("3 : Indiquer que la tâche n'est pas terminée");
            }
            System.out.println("4 : Retourner aux autres groupes de tâches");
            userAnswer = CLIController.readUserChoice(1, 4);

            switch(userAnswer){
                case 1:
                    selectUserTaskMenu(projectId, JSONTools.extractIntFromJSONObject(task, "id"));
                    break;
                case 2:
                    displayTaskCommits(projectId, JSONTools.extractIntFromJSONObject(task, "id"));
                    break;
                case 3:
                    APIController.getInstance().getAPITaskController().updateTaskIsDoneAttribut(projectId,
                            JSONTools.extractIntFromJSONObject(task, "id"),
                            JSONTools.extractStringFromJSONObject(task, "is_done").equals("false"));
            }
        } while(userAnswer != 4);
    }

    private static void displayTaskCommits(int projectId, int taskId) {
        JSONArray commits = APIController.getInstance().getAPITaskController().getAllCommitsForATask(projectId, taskId);
        System.out.println("=================");

        if(commits.size() == 0){
            System.out.println("Aucun commit n'a été renseigné avec cette tâche, pour en ajouter saisissez \"[" + taskId + "]\" dans les messages de vos commits pour pouvoir l'ajouter à cette tâche");
            return;
        }

        System.out.println("Voici les commits associés à cette tâche : ");
        for (Object commit : commits) {
            System.out.println(" - \"" + JSONTools.extractStringFromJSONObject((JSONObject) commit, "message") + "\"");
        }
        System.out.println();
    }

    private static void selectUserTaskMenu(int projectId, int taskId) {
        System.out.println("=================");
        APITaskController apiTaskController = APIController.getInstance().getAPITaskController();
        JSONArray users = apiTaskController.getUsersForATask(projectId, taskId);
        int userAnswer;

        if(users.size() == 0){
            System.out.println("Vous n'avez pas d'utilisateur assigné à cette tâche");
            System.out.println("Vous devriez donc en ajouter un");
        }

        do {
            if(users.size() != 0){
                System.out.println("Voici les utilisateur liés à cette tâche, sélectionnez en un pour le retirer de cette tâche");
            }
            for (int i = 0; i < users.size(); i++)
            {
                String username = JSONTools.extractStringFromJSONObject((JSONObject) users.get(i), "name");
                System.out.println((i + 1) + " : " + username);
            }
            System.out.println((users.size() + 1) + " : Ajouter un utilisateur");
            System.out.println((users.size() + 2) + " : Revenir au menu précédent");
            userAnswer = CLIController.readUserChoice(1, users.size() + 2);

            if(userAnswer <= users.size()){
                apiTaskController.deleteUserToATask(taskId,
                        JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer), "name"));
            }
            else if(userAnswer == users.size() + 1){
                addUser(projectId, taskId);
                users = apiTaskController.getUsersForATask(projectId, taskId);
            }
        } while(userAnswer != (users.size() + 2));
    }

    public static void addUser(int projectId, int taskId){
        System.out.println("=================");
        APITaskController apiTaskController = APIController.getInstance().getAPITaskController();
        JSONArray users = APIController.getInstance().getAPIProjectController().getUsersFromAProject(projectId);
        int userAnswer;
        do {
            System.out.println("Sélectionner l'utilisateur que vous voulez ajouter au projet");
            for (int i = 0; i < users.size(); i++)
            {
                String username = JSONTools.extractStringFromJSONObject((JSONObject) users.get(i), "username");
                System.out.println((i + 1) + " : " + username);
            }
            userAnswer = CLIController.readUserChoice(1, users.size());
        } while(apiTaskController.addUserToATask(taskId,
                JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer - 1), "username")) == null);

        System.out.println("L'utilisateur " + JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer - 1), "surname") + " à bien été ajouté à cette tâche");
    }
}
