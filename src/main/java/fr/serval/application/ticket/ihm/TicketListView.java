package fr.serval.application.ticket.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TicketListView implements IHMComponentBuilder {
    private final TreeView<String> ticketTree;
    private final TreeItem<String> ticketRoot;

    private final String[] ticketList = {"ticket001", "ticket002", "ticket003"};

    public TicketListView() {
        this.ticketTree = new TreeView<>();
        this.ticketRoot = new TreeItem<>();

        this.setupComponent();
    }

    public void updateTicketList() {
        this.ticketRoot.getChildren().clear();
        for (int i = ticketList.length - 1; i >= 0; i--) {
            this.ticketRoot.getChildren().add(new TreeItem<>(ticketList[i]));
        }
    }

    @Override
    public void setupComponent() {
        this.ticketTree.setRoot(this.ticketRoot);
        this.ticketTree.setShowRoot(false);
        this.ticketTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.ticketRoot.setExpanded(true);

        this.updateTicketList();
    }

    @Override
    public TreeView<String> getComponent() {
        return this.ticketTree;
    }
}
