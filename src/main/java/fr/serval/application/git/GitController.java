package fr.serval.application.git;

import fr.serval.application.git.ihm.GitCommitDetails;
import fr.serval.application.git.ihm.GitCommitList;
import fr.serval.application.git.ihm.GitCommitTask;
import fr.serval.application.project.Project;
import fr.serval.controller.Controller;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GitController implements Controller, IHMComponentBuilder {
    private final BorderPane gitMainView;
    private final VBox gitMainInfoView;

    private final GitCommitList gitCommitList;
    private final GitCommitDetails gitCommitDetails;
    private final GitCommitTask gitCommitTask;

    private Project project;

    public GitController(Project project) {
        this.gitMainView = new BorderPane();
        this.gitMainInfoView = new VBox();

        this.gitCommitList = new GitCommitList();
        this.gitCommitDetails = new GitCommitDetails();
        this.gitCommitTask = new GitCommitTask();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.gitMainView.setLeft(gitCommitList.getComponent());
        this.gitMainView.setCenter(this.gitMainInfoView);
        this.gitMainInfoView.getChildren().add(gitCommitDetails.getComponent());
        this.gitMainInfoView.getChildren().add(gitCommitTask.getComponent());
    }

    @Override
    public BorderPane getComponent() {
        return this.gitMainView;
    }

    protected Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
