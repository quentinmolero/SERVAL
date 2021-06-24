package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.application.task.ihm.NewTaskDialog;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class TicketTopView implements IHMComponentBuilder {
    private final GridPane topTicketView;
    private final GridPane topTicketTextListView;

    private final TicketListView ticketListView;

    private final Project project;

    private final Button newTicketButton;
    private final Text openedTicketText;
    private final Text closedTicketText;
    private final Text totalTicketText;

    public TicketTopView(TicketListView ticketListView, Project project) {
        this.topTicketView = new GridPane();
        this.topTicketTextListView = new GridPane();

        this.ticketListView = ticketListView;

        this.project = project;

        this.newTicketButton = new Button();
        this.openedTicketText = new Text();
        this.closedTicketText = new Text();
        this.totalTicketText = new Text();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.newTicketButton.setText("Nouveau Ticket");
        this.newTicketButton.setOnAction(event -> {
            final NewTicketDialog newTicketDialog = new NewTicketDialog(this.ticketListView, this.project);
            newTicketDialog.getComponent().show();
        });

        this.updateTicketNumbers();

        this.topTicketView.add(this.newTicketButton, 0, 0);
        this.topTicketView.add(this.topTicketTextListView, 1, 0);

        this.topTicketView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.topTicketView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.topTicketView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));

        this.topTicketTextListView.add(this.openedTicketText, 0, 0);
        this.topTicketTextListView.add(this.closedTicketText, 0, 1);
        this.topTicketTextListView.add(this.totalTicketText, 0, 2);

        this.topTicketTextListView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.topTicketTextListView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 33));
        this.topTicketTextListView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 33));
        this.topTicketTextListView.getRowConstraints().add(2, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 33));
    }

    public void updateTicketNumbers() {
        int openedTickets = 0;
        int closedTickets = 0;

        for (JSONObject ticket : this.ticketListView.getTicketHashMap().values()) {
            if (JSONTools.extractBooleanFromJSONObject(ticket, "is_closed")) {
                openedTickets++;
            } else {
                closedTickets++;
            }
        }

        this.openedTicketText.setText("Nombre de tickets ouverts: " + openedTickets);
        this.closedTicketText.setText("Nombre de tickets finis: " + closedTickets);
        this.totalTicketText.setText("Nombre de total de tickets: " + (openedTickets + closedTickets));
    }

    @Override
    public GridPane getComponent() {
        return this.topTicketView;
    }
}
