package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APIProjectController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProjectMenu {
    public static void main(){
        System.out.println("=================");

        int userAnswer;
        System.out.println("Bienvenue sur Serval CLI !");

        do {
            System.out.println("1 : Accéder à un projet");
            System.out.println("2 : Ajouter un nouveau projet");
            System.out.println("3 : Quitter Serval");

            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectProjectMenu();
                    break;
                case 2:
                    addProjectMenu();
                    break;
            }
        }
        while(userAnswer != 3);
    }

    private static void selectProjectMenu(){
        System.out.println("=================");

        int userAnswer;
        APIProjectController apiProjectController = APIController.getInstance().getAPIProjectController();
        JSONArray projects = apiProjectController.getCurrentUserProjects();

        if(projects == null){
            System.out.println("Vous n'avez pas enregistré de projet");
            return;
        }

        do {
            System.out.println("À quel project souhaitez vous accéder ?");
            for (int i = 0; i < projects.size(); i++)
            {
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) projects.get(i), "name");
                System.out.println((i + 1) + " : " + projectName);
            }
            System.out.println((projects.size() + 1) + " : Retourner à l'accueil");
            userAnswer = CLIController.readUserChoice(1, projects.size() + 1);

            if(userAnswer <= projects.size()){
                TaskGroupMenu.main((JSONObject) projects.get(userAnswer - 1));
            }
        } while(userAnswer != (projects.size() + 1));
    }

    private static void addProjectMenu(){
        System.out.println("=================");
        APIProjectController apiProjectController = APIController.getInstance().getAPIProjectController();
        String repo_name;
        do {
            System.out.println("Veuillez saisir le nom du dépôt Github que vous souhaitez ajouter en tant que projet : ");
            repo_name = CLIController.readStringUserInput();
        } while(apiProjectController.addProjectToCurrentUser(repo_name) == null);

        System.out.println("Le projet " + repo_name + " a bien été ajouté");
    }
}
