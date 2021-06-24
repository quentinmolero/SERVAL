package fr.serval.application.ticket;

import fr.serval.application.project.Project;
import fr.serval.application.ticket.ihm.TicketListView;
import fr.serval.application.ticket.ihm.TicketMainView;
import fr.serval.application.ticket.ihm.TicketTopView;
import fr.serval.controller.Controller;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TicketController implements Controller, IHMComponentBuilder {
    private final GridPane ticketView;
    private final GridPane ticketCenterView;

    private final TicketTopView ticketTopView;
    private final TicketListView ticketListView;
    private final TicketMainView ticketMainView;

    private final Project project;

    public TicketController(Project project) {
        this.ticketView = new GridPane();
        this.ticketCenterView = new GridPane();

        this.project = project;

        this.ticketMainView = new TicketMainView(this.project);
        this.ticketListView = new TicketListView(this.project, this.ticketMainView);
        this.ticketTopView = new TicketTopView(this.ticketListView, this.project);
        this.ticketMainView.setTicketTopView(this.ticketTopView);
        this.ticketMainView.setTicketListView(this.ticketListView);

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.ticketView.add(this.ticketTopView.getComponent(), 0, 0);
        this.ticketView.add(this.ticketCenterView, 0, 1);

        this.ticketView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.ticketView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.ticketView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 80));

        this.ticketCenterView.add(this.ticketListView.getComponent(), 0, 0);
        this.ticketCenterView.add(this.ticketMainView.getComponent(), 1, 0);

        this.ticketCenterView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 25));
        this.ticketCenterView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 75));
        this.ticketCenterView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));
    }

    @Override
    public GridPane getComponent() {
        return this.ticketView;
    }
}
