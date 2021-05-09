package fr.serval.application.ticket.ihm;

import fr.serval.Main;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Arrays;

public class TicketDetailView implements IHMComponentBuilder {
    private final GridPane ticketDetailView;
    private final ListView<ImageView> peopleView;
    private final ListView<String> commitList;

    private final String[] commits = {"Fix getter for UI", "Comply with clean code strategy", "Made UI responsive", "Fixed UI view"};

    public TicketDetailView() {
        this.ticketDetailView = new GridPane();
        this.peopleView = new ListView<>();
        this.commitList = new ListView<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.updateList();

        this.ticketDetailView.add(this.peopleView, 0, 0);
        this.ticketDetailView.add(this.commitList, 1, 0);

        this.ticketDetailView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.ticketDetailView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));
    }

    private void updateList() {
        ObservableList<ImageView> imageViewObservableList = this.peopleView.getItems();

        imageViewObservableList.clear();

        for (int i = 0; i < 3; i++) {
            Image image = new Image(String.valueOf(Main.class.getResource("images/people.jpg")));
            ImageView imageView = new ImageView();

            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            imageViewObservableList.add(imageView);
        }

        this.peopleView.setItems(imageViewObservableList);
        this.peopleView.refresh();

        ObservableList<String> stringObservableList = this.commitList.getItems();

        stringObservableList.clear();
        stringObservableList.addAll(Arrays.asList(this.commits));

        this.commitList.setItems(stringObservableList);
        this.commitList.refresh();
    }


    @Override
    public GridPane getComponent() {
        return this.ticketDetailView;
    }
}
