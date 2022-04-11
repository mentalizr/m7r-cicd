package org.mentalizr.cicd;

import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.taskRunner.runner.standard.StandardTaskRunner;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.appInit.AppInit;
import org.mentalizr.cicd.tasks.Build;

public class CicdCli {

    public static void main(String[] args) {

        AppInit.execute();

        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();
        Build.createTasks(taskRegistryBuilder);
        TaskRegistry taskRegistry = taskRegistryBuilder.build();

        TaskRunner taskRunner = StandardTaskRunner.create(taskRegistry, false, 33);
        TaskRunnerResult result = taskRunner.run("build");

        System.out.println("CICD");
    }

}
