package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.build.project.Project;
import org.mentalizr.cicd.build.project.ProjectRegistry;

import java.util.List;
import java.util.Objects;

public class Tasks {

    public static void createMultiProjectTasks(String targetName, TaskRegistryBuilder taskRegistryBuilder, ProjectRegistry projectRegistry) {
        List<Project> projectList = projectRegistry.getProjectListReversed();
        if (projectList.isEmpty()) throw new IllegalStateException("No projects specified.");

        Project lastProject = null;
        for (Project project : projectList) {
            Task task = Tasks.createProjectTask(targetName, project, lastProject);
            taskRegistryBuilder.withTask(task);
            lastProject = project;
        }

        Task buildTask = new TaskBuilder()
                .name(targetName)
                .dependencies(targetName + "-" + Objects.requireNonNull(lastProject).getName())
                .isTarget()
                .execute(() -> {})
                .build();
        taskRegistryBuilder.withTask(buildTask);
    }


    public static Task createProjectTask(String targetName, Project project, Project dependentProject) {
        TaskBuilder taskBuilder = new TaskBuilder()
                .name(targetName + "-" + project.getName());

        switch (targetName) {
            case "reset":
                taskBuilder.execute(project::reset);
                break;
            case "build":
                taskBuilder.execute(project::build);
                break;
            case "init":
                taskBuilder.isUpToDate(project::isInitialized);
                taskBuilder.execute(project::init);
                break;
            case "clean":
                taskBuilder.execute(project::clean);
                break;
        }

        if (dependentProject != null) {
            taskBuilder.dependencies(targetName + "-" + dependentProject.getName());
        }
        return taskBuilder.build();
    }

}