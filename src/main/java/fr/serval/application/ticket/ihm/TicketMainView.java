package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

public class TicketMainView implements IHMComponentBuilder {
    private final GridPane ticketMainView;
    private final GridPane ticketInfoView;

    private final Text ticketTitle;
    private final Text ticketDescription;

    private final Button closeTicketButton;

    private final Project project;

    private final TicketDetailView ticketDetailView;
    private TicketTopView ticketTopView;
    private TicketListView ticketListView;

    private int ticketId;

    public TicketMainView(Project project) {
        this.ticketMainView = new GridPane();
        this.ticketInfoView = new GridPane();

        this.ticketTitle = new Text();
        this.ticketDescription = new Text();

        this.closeTicketButton = new Button();

        this.project = project;

        this.ticketDetailView = new TicketDetailView(this.project);

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.closeTicketButton.setText("Fermer le ticket");
        this.closeTicketButton.setOnAction(event -> this.closeTicketAction());
        this.closeTicketButton.setDisable(true);

        this.ticketMainView.add(this.ticketInfoView, 0, 0);

        this.ticketMainView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.ticketMainView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));

        this.ticketInfoView.add(this.ticketTitle, 0, 0);
        this.ticketInfoView.add(this.ticketDescription, 0, 1);
        this.ticketInfoView.add(this.ticketDetailView.getComponent(), 0, 2);
        this.ticketInfoView.add(this.closeTicketButton, 0, 3);

        this.ticketInfoView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.ticketInfoView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 5));
        this.ticketInfoView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 5));
        this.ticketInfoView.getRowConstraints().add(2, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 80));
        this.ticketInfoView.getRowConstraints().add(3, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 10));
    }

    public void updateTicketData(JSONObject ticketJSONObject) {
        this.ticketTitle.setText(JSONTools.extractStringFromJSONObject(ticketJSONObject, "title"));
        this.ticketDescription.setText(JSONTools.extractStringFromJSONObject(ticketJSONObject, "description"));
        this.ticketId = JSONTools.extractIntFromJSONObject(ticketJSONObject, "id");
        if (JSONTools.extractBooleanFromJSONObject(ticketJSONObject, "is_closed")) {
            this.closeTicketButton.setDisable(true);
        } else {
            this.closeTicketButton.setDisable(false);
        }
    }

    private void closeTicketAction() {
        this.closeTicketButton.setDisable(true);
        APIController.getInstance().getAPITicketController().updateTicketIsClosedAttribut(this.ticketId, true);
        this.ticketListView.updateTicketList();
        this.ticketTopView.updateTicketNumbers();
    }

    @Override
    public GridPane getComponent() {
        return this.ticketMainView;
    }

    public TicketDetailView getTicketDetailView() {
        return ticketDetailView;
    }

    public void setTicketTopView(TicketTopView ticketTopView) {
        this.ticketTopView = ticketTopView;
    }

    public void setTicketListView(TicketListView ticketListView) {
        this.ticketListView = ticketListView;
        this.ticketDetailView.setTicketListView(this.ticketListView);
    }
}
