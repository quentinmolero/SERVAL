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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitCommitList implements IHMComponentBuilder {
    private final TreeView<String> commitTree;
    private final TreeItem<String> commitRoot;

    private final Map<String, JSONObject> commitHashMap;

    private final Project project;
    private final GitCommitDetails gitCommitDetails;
    private final GitCommitTask gitCommitTask;

    public GitCommitList(Project project, GitCommitDetails gitCommitDetails, GitCommitTask gitCommitTask) {
        this.commitTree = new TreeView<>();
        this.commitRoot = new TreeItem<>();

        this.commitHashMap = new HashMap<>();

        this.project = project;

        this.gitCommitDetails = gitCommitDetails;
        this.gitCommitTask = gitCommitTask;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.commitTree.setRoot(this.commitRoot);
        this.commitTree.setShowRoot(false);
        this.commitTree.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT, new Insets(1))));
        this.commitRoot.setExpanded(true);

        this.commitTree.getSelectionModel().selectedIndexProperty().addListener(e -> {
            TreeItem<String> selectedNode = this.commitTree.getSelectionModel().getSelectedItem();
            JSONObject selectedTask = this.commitHashMap.get(selectedNode.getValue().split("\n")[1]);
            this.gitCommitDetails.updateDetails(selectedTask);
            this.gitCommitTask.updateTaskList(JSONTools.extractStringFromJSONObject(selectedTask, "message"));
        });

        this.updateCommitList();
    }

    @Override
    public TreeView<String> getComponent() {
        return this.commitTree;
    }

    public void updateCommitList() {
        JSONObject projectJSONObject = APIController.getInstance().getAPIProjectController().getProjetCommit(this.project.getId());
        List<JSONObject> commitList = JSONTools.collectJSONArrayChildrenAsArrayList(JSONTools.extractJSONArrayFromJSONObject(projectJSONObject, "commits"));
        for (JSONObject commit : commitList) {
            String sha = JSONTools.extractStringFromJSONObject(commit, "sha");
            this.commitHashMap.put(sha, commit);
            String commitName = JSONTools.extractStringFromJSONObject(commit, "message") + '\n' + sha;
            this.commitRoot.getChildren().add(new TreeItem<>(commitName));
        }
    }
}
