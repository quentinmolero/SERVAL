package fr.serval.cli;

import fr.serval.api.APIController;
import fr.serval.api.APITicketController;
import fr.serval.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TicketMenu {
    public static void main(JSONObject project){
        System.out.println("=================");
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire avec " + JSONTools.extractStringFromJSONObject(project, "name") + " ?");
            System.out.println("1 : Voir les tickets");
            System.out.println("2 : Ajouter un tickets");
            System.out.println("3 : Retourner aux autres groupes de tickets");
            userAnswer = CLIController.readUserChoice(1, 3);

            switch(userAnswer){
                case 1:
                    selectTicketMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
                case 2:
                    addTicketMenu(JSONTools.extractIntFromJSONObject(project, "id"));
                    break;
            }
        } while(userAnswer != 3);
    }

    private static void selectTicketMenu(int projectId){
        System.out.println("=================");
        int userAnswer;
        APITicketController apiTicketController = APIController.getInstance().getAPITicketController();
        JSONArray tickets = apiTicketController.getProjetTickets(projectId);

        if(tickets.size() == 0){
            System.out.println("Vous n'avez pas enregistré de ticket");
            return;
        }

        do {
            System.out.println("À quel ticket souhaitez vous accéder ?");
            for (int i = 0; i < tickets.size(); i++)
            {
                String projectName = JSONTools.extractStringFromJSONObject((JSONObject) tickets.get(i), "title");
                String is_closed = "[Ouvert]";
                if(JSONTools.extractStringFromJSONObject((JSONObject) tickets.get(i), "is_closed").equals("true")){
                    is_closed = "[Fermé]";
                }
                System.out.println((i + 1) + " : " + is_closed + " " + projectName);
            }
            System.out.println((tickets.size() + 1) + " : Retourner au menu du projet");
            userAnswer = CLIController.readUserChoice(1, tickets.size() + 1);

            if(userAnswer <= tickets.size()){
                TicketMenu.ticketOptionsMenu(projectId, (JSONObject) tickets.get(userAnswer - 1));
            }
        } while(userAnswer != (tickets.size() + 1));
    }

    private static void addTicketMenu(int projectId){
        System.out.println("=================");
        APITicketController apiTicketController = APIController.getInstance().getAPITicketController();
        String ticketName;
        String ticketDescription;
        do {
            System.out.println("Veuillez saisir le nom du ticket que vous souhaitez créer : ");
            ticketName = CLIController.readStringUserInput();
            System.out.println("Veuillez saisir la description du ticket que vous souhaitez créer : ");
            ticketDescription = CLIController.readStringUserInput();
        } while(apiTicketController.addTicketToAProjet(projectId, ticketName, ticketDescription) == null);

        System.out.println("Le groupe de ticket " + ticketName + " a bien été ajouté");
    }

    private static void ticketOptionsMenu(int projectId, JSONObject ticket){
        System.out.println("=================");
        int userAnswer;
        do {
            System.out.println("Que souhaitez vous faire avec " + JSONTools.extractStringFromJSONObject(ticket, "title") + " ? ID = " + JSONTools.extractIntFromJSONObject(ticket, "id"));
            System.out.println("1 : Gérer les membres");
            System.out.println("2 : Voir les commits associés");
            if(JSONTools.extractStringFromJSONObject(ticket, "is_closed").equals("false")){
                System.out.println("3 : Indiquer que la ticket fermé");
            }
            else {
                System.out.println("3 : Indiquer que la ticket n'est plus fermé");
            }
            System.out.println("4 : Retourner aux autres tickets");
            userAnswer = CLIController.readUserChoice(1, 4);

            switch(userAnswer){
                case 1:
                    selectUserTicketMenu(projectId, JSONTools.extractIntFromJSONObject(ticket, "id"));
                    break;
                case 2:
                    displayTicketCommits(projectId, JSONTools.extractIntFromJSONObject(ticket, "id"));
                    break;
                case 3:
                    APIController.getInstance().getAPITicketController().updateTicketIsClosedAttribut(
                            JSONTools.extractIntFromJSONObject(ticket, "id"),
                            JSONTools.extractStringFromJSONObject(ticket, "is_closed").equals("false"));
            }
        } while(userAnswer != 4);
    }

    private static void displayTicketCommits(int projectId, int ticketId) {
        JSONArray commits = APIController.getInstance().getAPITicketController().getAllCommitsForATicket(projectId, ticketId);
        System.out.println("=================");

        if(commits.size() == 0){
            System.out.println("Aucun commit n'a été renseigné avec ce ticket, pour en ajouter saisissez \"{" + ticketId + "}\" dans les messages de vos commits pour pouvoir l'ajouter à cette ticket");
            return;
        }

        System.out.println("Voici les commits associés à cette ticket : ");
        for (Object commit : commits) {
            System.out.println(" - \"" + JSONTools.extractStringFromJSONObject((JSONObject) commit, "message") + "\"");
        }
        System.out.println();
    }

    private static void selectUserTicketMenu(int projectId, int ticketId) {
        System.out.println("=================");
        APITicketController apiTicketController = APIController.getInstance().getAPITicketController();
        JSONArray users = apiTicketController.getUsersForATicket(projectId, ticketId);
        int userAnswer;

        if(users.size() == 0){
            System.out.println("Vous n'avez pas d'utilisateur assigné à cette ticket");
            System.out.println("Vous devriez donc en ajouter un");
        }

        do {
            if(users.size() != 0){
                System.out.println("Voici les utilisateur liés à cette ticket, sélectionnez en un pour le retirer de cette ticket");
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
                apiTicketController.deleteUserToATicket(ticketId,
                        JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer), "name"));
            }
            else if(userAnswer == users.size() + 1){
                addUser(projectId, ticketId);
                users = apiTicketController.getUsersForATicket(projectId, ticketId);
            }
        } while(userAnswer != (users.size() + 2));
    }

    public static void addUser(int projectId, int ticketId){
        System.out.println("=================");
        APITicketController apiTicketController = APIController.getInstance().getAPITicketController();
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
        } while(apiTicketController.addUserToATicket(ticketId,
                JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer - 1), "username")) == null);

        System.out.println("L'utilisateur " + JSONTools.extractStringFromJSONObject((JSONObject) users.get(userAnswer - 1), "surname") + " à bien été ajouté à cette ticket");
    }
}
