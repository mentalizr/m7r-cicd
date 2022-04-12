package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.build.project.ProjectRegistry;

public class ResetTasks {

    public static void create(TaskRegistryBuilder taskRegistryBuilder, ProjectRegistry projectRegistry) {
        Tasks.createMultiProjectTasks("reset", taskRegistryBuilder, projectRegistry);
    }

}
