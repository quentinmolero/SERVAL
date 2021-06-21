package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.application.project.Project;
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

public class NewTicketDialog implements IHMComponentBuilder {
    private final Stage dialog;
    private final GridPane dialogRoot;
    private final HBox actionList;

    private final TextField titleField;
    private final TextField descriptionField;
    private final Button createTask;
    private final Button cancelTask;
    private final Text statusText;

    private final TicketListView ticketListView;

    private final Project project;

    public NewTicketDialog(TicketListView ticketListView, Project project) {
        this.dialog = new Stage();
        this.dialogRoot = new GridPane();
        this.actionList = new HBox();

        this.titleField = new TextField();
        this.descriptionField = new TextField();
        this.createTask = new Button();
        this.cancelTask = new Button();
        this.statusText = new Text();

        this.ticketListView = ticketListView;

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.titleField.setPromptText("Titre du ticket");
        this.descriptionField.setPromptText("Description du ticket");

        this.createTask.setText("Ajouter la tache");
        this.createTask.setOnAction(event -> this.addNewTicket());
        this.cancelTask.setText("Annuler");
        this.cancelTask.setOnAction(event -> this.dialog.close());

        this.statusText.fillProperty().setValue(Color.RED);

        this.actionList.getChildren().add(this.createTask);
        this.actionList.getChildren().add(this.cancelTask);

        this.dialogRoot.add(this.titleField, 0, 0);
        this.dialogRoot.add(this.descriptionField, 0, 1);
        this.dialogRoot.add(this.actionList, 0, 2);
        this.dialogRoot.add(this.statusText, 0, 3);
        this.dialogRoot.getColumnConstraints().add(GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 25));

        Scene dialogScene = new Scene(this.dialogRoot, 300, 200);

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ApplicationMainView.getMainStage());
        dialog.setScene(dialogScene);
        dialog.setTitle("Ajouter unr tache");
    }

    private void addNewTicket() {
        this.statusText.setText("");

        if (this.titleField.getText().isEmpty()) {
            this.statusText.setText("Le titre ne peux pas être vide");
            return;
        }

        if (this.descriptionField.getText().isEmpty()) {
            this.statusText.setText("La description ne peux pas être vide");
            return;
        }
        APIController.getInstance().getAPITicketController().addTicketToAProjet(this.project.getId(), this.titleField.getText(), this.descriptionField.getText());

        this.ticketListView.updateTicketList();
        this.dialog.close();
    }

    @Override
    public Stage getComponent() {
        return this.dialog;
    }
}
