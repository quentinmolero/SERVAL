package fr.serval.controller;

import fr.serval.application.project.Project;

import java.util.List;

public interface NodeController {

    void setProject(Project project);

    Project getProject();

    List<ProjectTreeNode> getNodeList();
}
