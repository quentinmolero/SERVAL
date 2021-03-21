package fr.serval.application.task.ihm;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;

import javax.swing.*;

public class TaskMainView implements IHMComponentBuilder {
    private final Project project;
    private JLabel label;

    public TaskMainView(Project project) {
        this.project = project;
        setupComponent();
    }

    @Override
    public void setupComponent() {
        this.label = new JLabel(this.project.getName());
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Object getComponent() {
        return this.label;
    }
}
