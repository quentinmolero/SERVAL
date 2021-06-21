package fr.serval.application.ticket.ihm;

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

    private final TicketDetailView ticketDetailView;

    public TicketMainView() {
        this.ticketMainView = new GridPane();
        this.ticketInfoView = new GridPane();

        this.ticketTitle = new Text();
        this.ticketDescription = new Text();

        this.closeTicketButton = new Button();

        this.ticketDetailView = new TicketDetailView();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.closeTicketButton.setText("Fermer le ticket");

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
        System.out.println(ticketJSONObject);
        this.ticketTitle.setText(JSONTools.extractStringFromJSONObject(ticketJSONObject, "title"));
        this.ticketDescription.setText(JSONTools.extractStringFromJSONObject(ticketJSONObject, "description"));
    }

    @Override
    public GridPane getComponent() {
        return this.ticketMainView;
    }
}
