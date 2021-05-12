package fr.serval.application.menu.settings.ihm;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectController;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageProjectDialog implements IHMComponentBuilder {
    private final Stage dialog;

    private final GridPane dialogRoot;
    private final VBox listProjectNames;
    private final VBox listProjectActions;

    private final TextField textField;
    private final Button addUserButton;
    private final Button removeUserButton;
    private final Text text;

    private final ToggleGroup toggleGroup;

    public ManageProjectDialog() {
        this.dialog = new Stage();

        this.dialogRoot = new GridPane();
        this.listProjectNames = new VBox(5);
        this.listProjectActions = new VBox(5);

        this.textField = new TextField();
        this.addUserButton = new Button();
        this.removeUserButton = new Button();
        this.text = new Text();

        this.toggleGroup = new ToggleGroup();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.fillProjectRadioList();

        this.textField.setPromptText("Nom d'utilisateur Github");
        this.setupButtons();
        this.text.fillProperty().setValue(Color.RED);

        this.listProjectActions.getChildren().add(this.textField);
        this.listProjectActions.getChildren().add(this.addUserButton);
        this.listProjectActions.getChildren().add(this.removeUserButton);
        this.listProjectActions.getChildren().add(this.text);

        this.dialogRoot.add(this.listProjectNames, 0, 0);
        this.dialogRoot.add(this.listProjectActions, 1, 0);
        this.dialogRoot.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.dialogRoot.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));

        Scene dialogScene = new Scene(this.dialogRoot, 400, 300);

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ApplicationMainView.getMainStage());
        dialog.setScene(dialogScene);
        dialog.setTitle("Gérer un projet");
    }

    private void fillProjectRadioList() {
        for (Project project : ProjectController.getInstance().getProjectList()) {
            RadioButton radioButton = new RadioButton(project.getName());
            radioButton.setToggleGroup(this.toggleGroup);
            this.listProjectNames.getChildren().add(radioButton);
        }
    }

    private void setupButtons() {
        this.addUserButton.setText("Ajouter au projet");
        this.addUserButton.setOnAction(event -> {
            this.addUserButton.setDisable(true);
            this.text.setText("");

            if (!this.textField.getText().isEmpty() && !this.textField.getText().isBlank()) {
                System.out.println(this.textField.getText());
            } else {
                this.text.setText("Le nom ne peux pas être vide");
            }

            this.addUserButton.setDisable(false);
        });

        this.removeUserButton.setText("Retirer du projet");
        this.removeUserButton.setOnAction(event -> {
            this.removeUserButton.setDisable(true);
            this.text.setText("");

            if (!this.textField.getText().isEmpty() && !this.textField.getText().isBlank()) {
                System.out.println(this.textField.getText());
            } else {
                this.text.setText("Le nom ne peux pas être vide");
            }

            this.removeUserButton.setDisable(false);
        });
    }

    @Override
    public Stage getComponent() {
        return this.dialog;
    }
}
