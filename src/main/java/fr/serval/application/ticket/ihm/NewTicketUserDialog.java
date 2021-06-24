package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewTicketUserDialog implements IHMComponentBuilder {
    private final Stage dialog;
    private final GridPane dialogRoot;
    private final HBox actionList;

    private final TextField usernameField;
    private final Button createTask;
    private final Button cancelTask;
    private final Text statusText;

    private final TicketDetailView ticketDetailView;

    private final int ticketId;

    public NewTicketUserDialog(TicketDetailView ticketDetailView, int ticketId) {
        this.dialog = new Stage();
        this.dialogRoot = new GridPane();
        this.actionList = new HBox();

        this.usernameField = new TextField();
        this.createTask = new Button();
        this.cancelTask = new Button();
        this.statusText = new Text();

        this.ticketDetailView = ticketDetailView;

        this.ticketId = ticketId;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.usernameField.setPromptText("Nom d'utilisateur Github");

        this.createTask.setText("Ajouter l'utilisateur");
        this.createTask.setOnAction(event -> this.addNewUser());
        this.cancelTask.setText("Annuler");
        this.cancelTask.setOnAction(event -> this.dialog.close());

        this.statusText.fillProperty().setValue(Color.RED);

        this.actionList.getChildren().add(this.createTask);
        this.actionList.getChildren().add(this.cancelTask);

        this.dialogRoot.add(this.usernameField, 0, 0);
        this.dialogRoot.add(this.actionList, 0, 1);
        this.dialogRoot.add(this.statusText, 0, 2);
        this.dialogRoot.getColumnConstraints().add(GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));

        Scene dialogScene = new Scene(this.dialogRoot, 300, 100);

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ApplicationMainView.getMainStage());
        dialog.setScene(dialogScene);
        dialog.setTitle("Ajouter un utilisateur");
    }

    private void addNewUser() {
        this.statusText.setText("");

        if (this.usernameField.getText().isEmpty()) {
            this.statusText.setText("Le titre ne peux pas être vide");
            return;
        }
        APIController.getInstance().getAPITicketController().addUserToATicket(this.ticketId, this.usernameField.getText());

        this.ticketDetailView.getTicketListView().updateTicketList();
        this.ticketDetailView.updateList(this.ticketId);

        this.dialog.close();
    }

    @Override
    public Stage getComponent() {
        return this.dialog;
    }
}