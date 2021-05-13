package fr.serval.application.menu.settings.ihm;

import fr.serval.api.APIController;
import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.application.project.Project;
import fr.serval.application.project.ProjectController;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
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
import org.json.simple.JSONObject;

public class ManageProjectDialog implements IHMComponentBuilder {
    private final Stage dialog;

    private final GridPane dialogRoot;
    private final VBox listProjectNames;
    private final VBox listProjectActions;
    private final VBox listUserRoles;

    private final TextField textField;

    private final Button addUserButton;
    private final Button removeUserButton;
    private final Text text;

    private final ToggleGroup projectToggleGroup;
    private final ToggleGroup roleToggleGroup;

    public ManageProjectDialog() {
        this.dialog = new Stage();

        this.dialogRoot = new GridPane();
        this.listProjectNames = new VBox(5);
        this.listProjectActions = new VBox(5);
        this.listUserRoles = new VBox(5);

        this.textField = new TextField();
        this.addUserButton = new Button();
        this.removeUserButton = new Button();
        this.text = new Text();

        this.projectToggleGroup = new ToggleGroup();
        this.roleToggleGroup = new ToggleGroup();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.fillProjectRadioList();
        this.fillUserRolesRadioList();

        this.textField.setPromptText("Nom d'utilisateur Github");
        this.setupButtons();
        this.text.fillProperty().setValue(Color.RED);

        this.listProjectActions.getChildren().add(this.textField);
        this.listProjectActions.getChildren().add(this.listUserRoles);
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
            radioButton.setToggleGroup(this.projectToggleGroup);
            this.listProjectNames.getChildren().add(radioButton);
        }
    }

    private void fillUserRolesRadioList() {
        for (JSONObject role : JSONTools.collectJSONArrayChildrenAsArrayList(APIController.getInstance().getAPIRoleController().getAllRoles()) ){
            RadioButton radioButton = new RadioButton(JSONTools.extractStringFromJSONObject(role, "label"));
            radioButton.setToggleGroup(this.roleToggleGroup);
            this.listUserRoles.getChildren().add(radioButton);
        }
    }

    private void setupButtons() {
        this.addUserButton.setText("Ajouter au projet");
        this.addUserButton.setOnAction(event -> {
            this.addUserButton.setDisable(true);
            this.text.setText("");

            String role = this.querySelectedRole();
            Integer projectId = this.querySelectedProjectId();

            if (this.checkStringNotNullEmptyOrBlank(this.textField.getText()) && projectId != null && this.checkStringNotNullEmptyOrBlank(role)) {
                APIController.getInstance().getAPIProjectController().addUserToProject(role, projectId, this.textField.getText());
            } else if (!this.checkStringNotNullEmptyOrBlank(this.textField.getText())) {
                this.text.setText("Le nom ne peux pas être vide");
            } else if (projectId == null) {
                this.text.setText("Merci de choisir un projet");
            } else if (!this.checkStringNotNullEmptyOrBlank(role)) {
                this.text.setText("Merci de choisir un role");
            }

            this.addUserButton.setDisable(false);
        });

        this.removeUserButton.setText("Retirer du projet");
        this.removeUserButton.setOnAction(event -> {
            this.removeUserButton.setDisable(true);
            this.text.setText("");

            Integer projectId = this.querySelectedProjectId();

            if (this.checkStringNotNullEmptyOrBlank(this.textField.getText()) && projectId != null) {
                APIController.getInstance().getAPIProjectController().removeUserFromAProject(projectId, this.textField.getText());
            } else if (!this.checkStringNotNullEmptyOrBlank(this.textField.getText())) {
                this.text.setText("Le nom ne peux pas être vide");
            } else if (projectId == null) {
                this.text.setText("Merci de choisir un projet");
            }

            this.removeUserButton.setDisable(false);
        });
    }

    private boolean checkStringNotNullEmptyOrBlank(String str) {
        return str != null && !str.isEmpty() && !str.isBlank();
    }

    private Integer querySelectedProjectId() {
        RadioButton radioButton = (RadioButton) this.projectToggleGroup.getSelectedToggle();
        if (radioButton == null) {
            return null;
        }

        Project project = ProjectController.getInstance().getProjectFromProjectName(radioButton.getText());
        if (project != null) {
            return project.getId();
        }

        return null;
    }

    private String querySelectedRole() {
        RadioButton radioButton = (RadioButton) this.roleToggleGroup.getSelectedToggle();
        if (radioButton == null) {
            return null;
        }

        return radioButton.getText();
    }

    @Override
    public Stage getComponent() {
        return this.dialog;
    }
}
