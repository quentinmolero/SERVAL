package fr.serval.application.git.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GitCommitList implements IHMComponentBuilder {
    private final TreeView<String> commitTree;
    private final TreeItem<String> commitRoot;

    private final String[] commitList = {"Initial commit", "First file", "Second file"};

    public GitCommitList() {
        this.commitTree = new TreeView<>();
        this.commitRoot = new TreeItem<>();

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
        this.commitRoot.getChildren().clear();
        for (int i = commitList.length - 1; i >= 0; i--) {
            this.commitRoot.getChildren().add(new TreeItem<>(commitList[i]));
        }
    }
}
