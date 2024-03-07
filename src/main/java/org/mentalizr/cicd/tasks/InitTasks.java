package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.projectModel.ProjectModel;

public class InitTasks {

    public static void create(TaskRegistryBuilder taskRegistryBuilder, ProjectModel projectModel) {
        Tasks.createMultiProjectTasks("init", taskRegistryBuilder, projectModel);
    }

}
