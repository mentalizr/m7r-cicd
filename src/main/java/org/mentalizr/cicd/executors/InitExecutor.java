package org.mentalizr.cicd.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.taskRunner.runner.standard.StandardTaskRunner;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.cicd.ExecutionContext;
import org.mentalizr.cicd.build.project.ProjectRegistry;
import org.mentalizr.cicd.project.Projects;
import org.mentalizr.cicd.tasks.InitTasks;

public class InitExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("initialize all projects ...");

        ProjectRegistry projectRegistry = Projects.create();
        TaskRegistry taskRegistry = createTaskRegistry(projectRegistry);
        TaskRunnerResult result =
                StandardTaskRunner
                        .create(taskRegistry, true, 33)
                        .run("init");

        if (!result.isSuccess()) throw new CommandExecutorException();
    }

    private TaskRegistry createTaskRegistry(ProjectRegistry projectRegistry) {
        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();
        InitTasks.create(taskRegistryBuilder, projectRegistry);
        return taskRegistryBuilder.build();
    }

}
