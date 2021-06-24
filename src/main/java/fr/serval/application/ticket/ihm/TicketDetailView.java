package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;

public class TicketDetailView implements IHMComponentBuilder {
    private final GridPane ticketDetailView;
    private final GridPane ticketPeopleDetailsView;
    private final ListView<String> peopleView;
    private final ListView<String> commitList;

    private final Button addUser;

    private final Project project;

    private final String[] commits = {"Fix getter for UI", "Comply with clean code strategy", "Made UI responsive", "Fixed UI view"};

    private TicketListView ticketListView;

    private int ticketId;

    public TicketDetailView(Project project) {
        this.ticketDetailView = new GridPane();
        this.ticketPeopleDetailsView = new GridPane();
        this.peopleView = new ListView<>();
        this.commitList = new ListView<>();

        this.addUser = new Button();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.addUser.setDisable(true);
        this.addUser.setText("Ajouter un utilisateur");
        this.addUser.setOnAction(event -> this.addUserAction());

        this.ticketPeopleDetailsView.add(this.peopleView, 0, 0);
        this.ticketPeopleDetailsView.add(this.addUser, 0, 1);
        this.ticketDetailView.add(this.ticketPeopleDetailsView, 0, 0);
        this.ticketDetailView.add(this.commitList, 1, 0);

        this.ticketPeopleDetailsView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.ticketPeopleDetailsView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 80));
        this.ticketPeopleDetailsView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.ticketDetailView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));
    }

    public void updateList(int ticketId) {
        this.ticketId = ticketId;
        JSONArray peopleList = APIController.getInstance().getAPITicketController().getUsersForATicket(this.project.getId(), ticketId);
        ObservableList<String> peopleViewObservableList = this.peopleView.getItems();

        peopleViewObservableList.clear();

        for (JSONObject people : JSONTools.collectJSONArrayChildrenAsArrayList(peopleList)) {
            peopleViewObservableList.add(JSONTools.extractStringFromJSONObject(people, "username"));
        }

        this.peopleView.setItems(peopleViewObservableList);
        this.peopleView.refresh();

        ObservableList<String> stringObservableList = this.commitList.getItems();

        stringObservableList.clear();
        stringObservableList.addAll(Arrays.asList(this.commits));

        this.commitList.setItems(stringObservableList);
        this.commitList.refresh();

        this.addUser.setDisable(false);
    }

    private void addUserAction() {
        this.addUser.setDisable(true);
        final NewTicketUserDialog newTicketUserDialog = new NewTicketUserDialog(this, this.ticketId);
        newTicketUserDialog.getComponent().show();
        this.addUser.setDisable(false);
    }

    @Override
    public GridPane getComponent() {
        return this.ticketDetailView;
    }

    public void setTicketListView(TicketListView ticketListView) {
        this.ticketListView = ticketListView;
    }

    public TicketListView getTicketListView() {
        return ticketListView;
    }
}
