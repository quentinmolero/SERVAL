package fr.serval.application.task.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskDetailsView implements IHMComponentBuilder {
    private final BorderPane taskDetailsView;
    private final Label taskDescription;
    private final FlowPane actorPane;
    private final Button finishTask;

    public TaskDetailsView() {
        this.taskDetailsView = new BorderPane();
        this.taskDescription = new Label();
        this.actorPane = new FlowPane();
        this.finishTask = new Button();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.taskDetailsView.setTop(this.taskDescription);
        this.taskDetailsView.setCenter(this.actorPane);
        this.taskDetailsView.setBottom(this.finishTask);
        this.taskDetailsView.setPrefWidth(200);

        this.taskDescription.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.taskDescription.setText("Description de la tache");

        this.actorPane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));

        this.finishTask.setText("Finir la tache");
    }

    @Override
    public BorderPane getComponent() {
        return this.taskDetailsView;
    }
}
