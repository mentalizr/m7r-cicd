package org.mentalizr.cicd.projectModel;

import de.arthurpicht.utils.struct.dag.manager.DagManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectModel {

    private final DagManager dagManager;

    public ProjectModel(DagManager dagManager) {
        this.dagManager = dagManager;
    }

    public Project getProject(String projectId) {
        return (Project) this.dagManager.getDagElement(projectId);
    }

    public List<Project> getSortedProjects(String entryPointId) {
        List<String> projectIdList = this.dagManager.getElementIdsInTopologicalSort(entryPointId);
        List<Project> projectList = new ArrayList<>();
        for (String projectId : projectIdList) {
            Project project = getProject(projectId);
            projectList.add(project);
        }
        Collections.reverse(projectList);
        return projectList;
    }

}
