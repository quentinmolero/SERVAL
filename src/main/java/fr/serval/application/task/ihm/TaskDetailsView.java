package fr.serval.application.task.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskDetailsView implements IHMComponentBuilder {
    private final GridPane taskDetailsView;
    private final Label taskDescription;
    private final FlowPane actorPane;
    private final Button finishTask;

    public TaskDetailsView() {
        this.taskDetailsView = new GridPane();
        this.taskDescription = new Label();
        this.actorPane = new FlowPane();
        this.finishTask = new Button();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskDetailsView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.taskDetailsView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 10));
        this.taskDetailsView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 70));
        this.taskDetailsView.getRowConstraints().add(2, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 20));

        this.taskDetailsView.add(this.taskDescription, 0, 0);
        this.taskDetailsView.add(this.actorPane, 0, 1);
        this.taskDetailsView.add(this.finishTask, 0, 2);

        this.taskDescription.setText("Description de la tache");

        this.actorPane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));

        this.finishTask.setText("Finir la tache");
    }

    @Override
    public GridPane getComponent() {
        return this.taskDetailsView;
    }
}
