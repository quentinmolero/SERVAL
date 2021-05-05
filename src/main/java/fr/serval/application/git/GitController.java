package fr.serval.application.git;

import fr.serval.application.git.ihm.GitCommitDetails;
import fr.serval.application.git.ihm.GitCommitList;
import fr.serval.application.git.ihm.GitCommitTask;
import fr.serval.application.project.Project;
import fr.serval.controller.Controller;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.scene.layout.*;

public class GitController implements Controller, IHMComponentBuilder {
    private final GridPane gitMainView;
    private final GridPane gitMainInfoView;

    private final GitCommitList gitCommitList;
    private final GitCommitDetails gitCommitDetails;
    private final GitCommitTask gitCommitTask;

    private Project project;

    public GitController(Project project) {
        this.gitMainView = new GridPane();
        this.gitMainInfoView = new GridPane();

        this.gitCommitList = new GitCommitList();
        this.gitCommitDetails = new GitCommitDetails();
        this.gitCommitTask = new GitCommitTask();

        this.project = project;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.gitMainInfoView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.gitMainInfoView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 50));

        this.gitMainInfoView.add(gitCommitDetails.getComponent(), 0, 0);
        this.gitMainInfoView.add(gitCommitTask.getComponent(), 0, 1);

        this.gitMainView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));
        this.gitMainView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 50));

        this.gitMainView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));

        this.gitMainView.add(this.gitCommitList.getComponent(), 0, 0);
        this.gitMainView.add(this.gitMainInfoView, 1, 0);
    }

    @Override
    public GridPane getComponent() {
        return this.gitMainView;
    }

    protected Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
