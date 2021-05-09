package fr.serval.application.ticket.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class TicketTopView implements IHMComponentBuilder {
    private final GridPane topTicketView;
    private final GridPane topTicketTextListView;

    private final Button newTicketButton;
    private final Text openedTicketText;
    private final Text closedTicketText;
    private final Text totalTicketText;

    public TicketTopView() {
        this.topTicketView = new GridPane();
        this.topTicketTextListView = new GridPane();

        this.newTicketButton = new Button();
        this.openedTicketText = new Text();
        this.closedTicketText = new Text();
        this.totalTicketText = new Text();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.newTicketButton.setText("Nouveau Ticket");

        this.openedTicketText.setText("Nombre de tickets ouverts: 3");
        this.closedTicketText.setText("Nombre de tickets finis: 0");
        this.totalTicketText.setText("Nombre de total de tickets: 3");

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

    @Override
    public GridPane getComponent() {
        return this.topTicketView;
    }
}
