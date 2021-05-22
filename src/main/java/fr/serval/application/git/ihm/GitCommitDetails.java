package fr.serval.application.git.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

public class GitCommitDetails implements IHMComponentBuilder {
    private final GridPane gitDetailsView;

    private final Label commitDate;
    private final Label commitAuthor;
    private final Label commitEmail;
    private final Label commitMessage;
    private final Label commitSha;

    public GitCommitDetails() {
        this.gitDetailsView = new GridPane();

        this.commitDate = new Label();
        this.commitAuthor = new Label();
        this.commitEmail = new Label();
        this.commitMessage = new Label();
        this.commitSha = new Label();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.commitDate.setWrapText(true);
        this.commitAuthor.setWrapText(true);
        this.commitEmail.setWrapText(true);
        this.commitMessage.setWrapText(true);
        this.commitSha.setWrapText(true);

        this.gitDetailsView.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.gitDetailsView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));

        this.gitDetailsView.add(this.commitDate, 0, 0);
        this.gitDetailsView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.gitDetailsView.add(this.commitAuthor, 0, 1);
        this.gitDetailsView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.gitDetailsView.add(this.commitEmail, 0, 2);
        this.gitDetailsView.getRowConstraints().add(2, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.gitDetailsView.add(this.commitMessage, 0, 3);
        this.gitDetailsView.getRowConstraints().add(3, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
        this.gitDetailsView.add(this.commitSha, 0, 4);
        this.gitDetailsView.getRowConstraints().add(4, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));
    }

    public void updateDetails(JSONObject commitJSONObject) {
        JSONObject commitData = JSONTools.extractJSONObjectFromJSONObject(commitJSONObject, "commits");
        this.commitDate.setText(JSONTools.extractStringFromJSONObject(commitData, "date"));
        this.commitAuthor.setText(JSONTools.extractStringFromJSONObject(commitData, "name"));
        this.commitEmail.setText(JSONTools.extractStringFromJSONObject(commitData, "email"));
        this.commitMessage.setText(JSONTools.extractStringFromJSONObject(commitJSONObject, "message"));
        this.commitSha.setText(JSONTools.extractStringFromJSONObject(commitJSONObject, "sha"));
    }

    @Override
    public GridPane getComponent() {
        return this.gitDetailsView;
    }
}
