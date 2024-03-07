package org.mentalizr.cicd.build.project;

import org.mentalizr.cicd.projectModel.Project;

import java.util.*;

public class ProjectRegistry {

    private final Map<String, Project> projectMap;

    public ProjectRegistry() {
        this.projectMap = new LinkedHashMap<>();
    }

    public void add(Project project) {
        String projectName = project.getName();
        if (this.projectMap.containsKey(projectName))
            throw new IllegalArgumentException(ProjectRegistry.class.getSimpleName()
                    + " already contains project with name + [" + projectName + "].");
        this.projectMap.put(projectName, project);
    }

    public List<Project> getProjectList() {
        return new ArrayList<>(this.projectMap.values());
    }

    public List<Project> getProjectListReversed() {
        List<Project> projectList = getProjectList();
        Collections.reverse(projectList);
        return projectList;
    }

}
