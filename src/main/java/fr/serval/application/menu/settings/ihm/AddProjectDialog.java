package fr.serval.application.menu.settings.ihm;

import fr.serval.api.APIController;
import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.application.project.ProjectController;
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

public class AddProjectDialog implements IHMComponentBuilder {
    private final Stage dialog;
    private final GridPane dialogRoot;
    private final HBox actionList;

    private final TextField projectNameField;
    private final Button addProject;
    private final Button cancelAddProject;
    private final Text statusText;

    public AddProjectDialog() {
        this.dialog = new Stage();
        this.dialogRoot = new GridPane();
        this.actionList = new HBox();

        this.projectNameField = new TextField();
        this.addProject = new Button();
        this.cancelAddProject = new Button();
        this.statusText = new Text();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.projectNameField.setPromptText("Nom du projet");

        this.addProject.setText("Ajouter le projet");
        this.addProject.setOnAction(event -> this.addNewTicket());
        this.cancelAddProject.setText("Annuler");
        this.cancelAddProject.setOnAction(event -> this.dialog.close());

        this.statusText.fillProperty().setValue(Color.RED);

        this.actionList.getChildren().add(this.addProject);
        this.actionList.getChildren().add(this.cancelAddProject);

        this.dialogRoot.add(this.projectNameField, 0, 0);
        this.dialogRoot.add(this.actionList, 0, 1);
        this.dialogRoot.add(this.statusText, 0, 2);
        this.dialogRoot.getColumnConstraints().add(GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 34));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 33));
        this.dialogRoot.getRowConstraints().add(GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 33));

        Scene dialogScene = new Scene(this.dialogRoot, 300, 100);

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ApplicationMainView.getMainStage());
        dialog.setScene(dialogScene);
        dialog.setTitle("Ajouter un projet");
    }

    private void addNewTicket() {
        this.statusText.setText("");

        if (this.projectNameField.getText().isEmpty()) {
            this.statusText.setText("Le nom du projet ne peux pas être vide");
            return;
        }
        APIController.getInstance().getAPIProjectController().addProjectToCurrentUser(this.projectNameField.getText());

        ProjectController.getInstance().fillProjectList();

        this.dialog.close();
    }

    @Override
    public Stage getComponent() {
        return this.dialog;
    }
}
