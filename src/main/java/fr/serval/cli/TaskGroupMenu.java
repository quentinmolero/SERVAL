package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APIProjectController;
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
            System.out.println("3 : Gérer les tickets");
            System.out.println("4 : Gérer les membres du groupe");
            System.out.println("5 : Retourner aux autres projets");
            userAnswer = CLIController.readUserChoice(1, 5);

            switch(userAnswer){
                case 1:
                    selectTaskGroupMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
                case 2:
                    addTaskGroupMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
                case 3:
                    TicketMenu.main(project);
                    break;
                case 4:
                    selectUserProjectMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
            }
        } while(userAnswer != 5);
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
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) taskGroups.get(i), "username");
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

    private static void selectUserProjectMenu(int projectId) {
        System.out.println("=================");
        APIProjectController apiProjectController = APIController.getInstance().getAPIProjectController();
        JSONArray users = apiProjectController.getUsersFromAProject(projectId);
        int userAnswer;

        if(users.size() == 0){
            System.out.println("Vous n'avez pas d'utilisateur assigné à ce projet");
            System.out.println("Vous devriez donc en ajouter un");
        }

        do {
            if(users.size() != 0){
                System.out.println("Voici les utilisateur liés à ce projet, sélectionnez en un pour le retirer de ce projet");
            }
            for (int i = 0; i < users.size(); i++)
            {
                String username = JSONTools.extractStringFromJSONObject((JSONObject) users.get(i), "username");
                System.out.println((i + 1) + " : " + username);
            }
            System.out.println((users.size() + 1) + " : Ajouter un utilisateur");
            System.out.println((users.size() + 2) + " : Revenir au menu précédent");
            userAnswer = CLIController.readUserChoice(1, users.size() + 2);

            if(userAnswer <= users.size()){
                apiProjectController.removeUserFromAProject(projectId,
                        JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer - 1), "username"));
            }
            else if(userAnswer == users.size() + 1){
                addUser(projectId);
                users = apiProjectController.getUsersFromAProject(projectId);
            }
        } while(userAnswer != (users.size() + 2));
    }

    public static void addUser(int projectId){
        System.out.println("=================");
        APIProjectController apiProjectController = APIController.getInstance().getAPIProjectController();
        JSONArray roles = APIController.getInstance().getAPIRoleController().getAllRoles();
        String name;
        int userAnswer;

        do {
            System.out.println("Veuillez saisir le nom de l'utilisateur que vous souhaitez ajouter à ce projet : ");
            name = CLIController.readStringUserInput();
        } while(name == null);

        System.out.println("Sélectionner de rôle l'utilisateur que vous voulez ajouter au projet");
        for (int i = 0; i < roles.size(); i++)
        {
            String username = JSONTools.extractStringFromJSONObject((JSONObject) roles.get(i), "label");
            System.out.println((i + 1) + " : " + username);
        }
        userAnswer = CLIController.readUserChoice(1, roles.size());

        apiProjectController.addUserToProject(JSONTools.extractStringFromJSONObject((JSONObject) roles.get(userAnswer - 1), "label"), projectId, name);

        System.out.println("L'utilisateur " + name + " à bien été ajouté à cette tâche en tant que " + JSONTools.extractStringFromJSONObject((JSONObject) roles.get(userAnswer - 1), "label"));
    }
}
