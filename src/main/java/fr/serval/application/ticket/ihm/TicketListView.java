package fr.serval.application.ticket.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketListView implements IHMComponentBuilder {
    private final TreeView<String> ticketTree;
    private final TreeItem<String> ticketRoot;

    private final TicketMainView ticketMainView;
    private final TicketDetailView ticketDetailView;

    private final Project project;

    private final Map<String, JSONObject> ticketHashMap;

    public TicketListView(Project project, TicketMainView ticketMainView) {
        this.ticketTree = new TreeView<>();
        this.ticketRoot = new TreeItem<>();

        this.ticketMainView = ticketMainView;
        this.ticketDetailView = this.ticketMainView.getTicketDetailView();

        this.project = project;

        this.ticketHashMap = new HashMap<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.ticketTree.setRoot(this.ticketRoot);
        this.ticketTree.setShowRoot(false);
        this.ticketTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.ticketRoot.setExpanded(true);

        this.ticketTree.getSelectionModel().selectedIndexProperty().addListener(e -> {
            TreeItem<String> selectedNode = this.ticketTree.getSelectionModel().getSelectedItem();
            JSONObject selectedTicket = this.ticketHashMap.get(selectedNode.getValue());
            this.ticketMainView.updateTicketData(selectedTicket);
            this.ticketMainView.getTicketDetailView().updateList(JSONTools.extractIntFromJSONObject(selectedTicket, "id"));
        });

        this.updateTicketList();
    }

    public void updateTicketList() {
        this.ticketRoot.getChildren().clear();
        JSONArray ticketList = APIController.getInstance().getAPITicketController().getProjetTickets(this.project.getId());
        List<JSONObject> tickets = JSONTools.collectJSONArrayChildrenAsArrayList(ticketList);
        for (JSONObject ticket : tickets) {
            String ticketTitle = JSONTools.extractStringFromJSONObject(ticket, "title");

            if (JSONTools.extractBooleanFromJSONObject(ticket, "is_closed")) {
                ticketTitle = '✅' + ticketTitle;
            }

            this.ticketRoot.getChildren().add(new TreeItem<>(ticketTitle));
            this.ticketHashMap.put(ticketTitle, ticket);
        }
    }

    @Override
    public TreeView<String> getComponent() {
        return this.ticketTree;
    }

    public Map<String, JSONObject> getTicketHashMap() {
        return ticketHashMap;
    }
}
