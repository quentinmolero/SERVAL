package fr.serval.application.git.ihm;

import fr.serval.api.APIController;
import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.JSONTools;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class GitCommitList implements IHMComponentBuilder {
    private final TreeView<String> commitTree;
    private final TreeItem<String> commitRoot;

    private final Project project;

    public GitCommitList(Project project) {
        this.commitTree = new TreeView<>();
        this.commitRoot = new TreeItem<>();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.commitTree.setRoot(this.commitRoot);
        this.commitTree.setShowRoot(false);
        this.commitTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.commitRoot.setExpanded(true);

        this.updateCommitList();
    }

    @Override
    public TreeView<String> getComponent() {
        return this.commitTree;
    }

    public void updateCommitList() {
        try {
            JSONObject projectJSONObject = APIController.getInstance().getAPIProjectController().getProjetCommit(this.project.getName());
            List<JSONObject> commitList = JSONTools.collectJSONArrayChildrenAsArrayList(JSONTools.extractJSONArrayFromJSONObject(projectJSONObject, "commits"));
            for (JSONObject commit : commitList) {
                this.commitRoot.getChildren().add(new TreeItem<>(JSONTools.extractStringFromJSONObject(commit, "message")));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
