package fr.serval.application.projects.ihm;

import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ProjectTreeView implements IHMComponentBuilder {
    private final JTree tree;
    private final DefaultMutableTreeNode root;

    public ProjectTreeView() {
        this.tree = new JTree();
        this.root = (DefaultMutableTreeNode) this.tree.getModel().getRoot();

        setupComponent();
    }

    @Override
    public void setupComponent() {
        this.tree.setRootVisible(false);
    }

    @Override
    public JTree getComponent() {
        return this.tree;
    }

    public void insertProjectNode(String projectName) {
        this.root.add(new DefaultMutableTreeNode(projectName));
        ((DefaultTreeModel) this.tree.getModel()).reload();
    }

    public void resetProjectTree() {
        this.root.removeAllChildren();
    }
}
