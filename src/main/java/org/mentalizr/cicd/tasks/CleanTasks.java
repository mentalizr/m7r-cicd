package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.projectModel.ProjectModel;

public class CleanTasks {

    public static void create(TaskRegistryBuilder taskRegistryBuilder, ProjectModel projectModel) {
        Tasks.createMultiProjectTasks("clean", taskRegistryBuilder, projectModel);
    }

}
