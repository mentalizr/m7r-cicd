package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.projectModel.ProjectModel;

public class BuildTasks {

    public static void create(TaskRegistryBuilder taskRegistryBuilder, ProjectModel projectModel) {
        Tasks.createMultiProjectTasks("build", taskRegistryBuilder, projectModel);
    }

}
