package org.mentalizr.cicd.projectModel;

import de.arthurpicht.utils.struct.dag.manager.DagManager;

import java.util.List;

public class ProjectModel {

    private final DagManager dagManager;

    public ProjectModel(DagManager dagManager) {
        this.dagManager = dagManager;
    }

    public Project getProject(String projectId) {
        return (Project) this.dagManager.getDagElement(projectId);
    }

    public List<String> getSortedProjects(String entryPointId) {
        return this.dagManager.getElementIdsInTopologicalSort(entryPointId);
    }

}
