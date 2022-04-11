package org.mentalizr.cicd.tasks;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.project.ContentManagerCli;

public class Build {

    public static void createTasks(TaskRegistryBuilder taskRegistryBuilder) {
        taskRegistryBuilder
                .withTask(build())
                .withTask(buildContentManagerCli());

    }

    private static Task build() {
        return new TaskBuilder()
                .name("build")
                .dependencies("build-content-manager-cli")
                .isTarget()
                .execute(() -> {})
                .build();
    }

    private static Task buildContentManagerCli() {
        return new TaskBuilder()
                .name("build-Content-manager-cli")
                .execute(() -> new ContentManagerCli().build())
                .build();
    }

}
