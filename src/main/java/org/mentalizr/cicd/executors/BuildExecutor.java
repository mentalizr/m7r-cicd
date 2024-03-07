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
import org.mentalizr.cicd.project.Projects;
import org.mentalizr.cicd.projectModel.ProjectModel;
import org.mentalizr.cicd.tasks.BuildTasks;
import org.mentalizr.cicd.tasks.InitTasks;

public class BuildExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("build all projects ...");

        ProjectModel projectModel = Projects.create();

        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();
        InitTasks.create(taskRegistryBuilder, projectModel);
        BuildTasks.create(taskRegistryBuilder, projectModel);
        TaskRegistry taskRegistry = taskRegistryBuilder.build();

        TaskRunner taskRunner = StandardTaskRunner.create(taskRegistry, true, 33);
        TaskRunnerResult result = taskRunner.run("init");
        if (!result.isSuccess()) throw new CommandExecutorException();

        result = taskRunner.run("build");
        if (!result.isSuccess()) throw new CommandExecutorException();
    }

}
