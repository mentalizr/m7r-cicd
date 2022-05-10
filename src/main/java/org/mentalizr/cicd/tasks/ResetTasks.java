package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.build.project.ProjectRegistry;
import org.mentalizr.cicd.projectModel.ProjectModel;

public class ResetTasks {

    public static void create(TaskRegistryBuilder taskRegistryBuilder, ProjectModel projectModel) {
        Tasks.createMultiProjectTasks("reset", taskRegistryBuilder, projectModel);
    }

}
