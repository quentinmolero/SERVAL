package fr.serval.application.ticket.ihm;

import fr.serval.Main;
import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;

public class TicketDetailView implements IHMComponentBuilder {
    private final GridPane ticketDetailView;
    private final ListView<String> peopleView;
    private final ListView<String> commitList;

    private final Project project;

    private final String[] commits = {"Fix getter for UI", "Comply with clean code strategy", "Made UI responsive", "Fixed UI view"};

    public TicketDetailView(Project project) {
        this.ticketDetailView = new GridPane();
        this.peopleView = new ListView<>();
        this.commitList = new ListView<>();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.ticketDetailView.add(this.peopleView, 0, 0);
        this.ticketDetailView.add(this.commitList, 1, 0);

        this.ticketDetailView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));
    }

    public void updateList(int ticketId) {
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
    }


    @Override
    public GridPane getComponent() {
        return this.ticketDetailView;
    }
}
