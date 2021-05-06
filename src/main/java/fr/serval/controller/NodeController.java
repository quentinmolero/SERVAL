package fr.serval.controller;

import fr.serval.application.project.Project;
import fr.serval.ihm.IHMComponentBuilder;

import java.util.List;

public interface NodeController {

    void setProject(Project project);

    List<ProjectTreeNode> getNodeList();
}
