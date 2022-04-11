package org.mentalizr.cicd.build.gradle;

import de.arthurpicht.processExecutor.*;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardErrorHandler;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardOutHandler;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.cicd.ExecutionContext;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.BuildContext;
import org.mentalizr.cicd.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Gradle {

    private static final Logger logger = LoggerFactory.getLogger("gradle");

    public static void build(Path projectDir) throws BuildException {
        BuildContext buildContext = ExecutionContext.getGradleExecutionContext();
        String[] commands = StringUtils.concatArrays(new String[]{"gradlew", "--console=plain"}, "build");
        userOutput(buildContext, commands);
        ProcessResultCollection result;

        try{
            result = execute(
                    projectDir,
                    buildContext.getLogger(),
                    buildContext.isVerbose(),
                    commands
            );
        } catch (ProcessExecutionException e) {
            throw new BuildException("Gradle build failed: " + e.getMessage(), e);
        }
        if (result.isFail()) throw createException(result);
    }

    private static ProcessResultCollection execute(Path projectDir, Logger logger, boolean verbose, String... commands) throws ProcessExecutionException {
        GeneralStandardOutHandler stdOutHandler = new GeneralStandardOutHandler(logger, verbose);
        GeneralStandardErrorHandler stdErrorHandler = new GeneralStandardErrorHandler(logger, verbose);
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withWorkingDirectory(projectDir)
                .withCommands(commands)
                .withStandardOutHandler(stdOutHandler)
                .withStandardErrorHandler(stdErrorHandler)
                .build();

        processExecutor.execute();

        return new ProcessResultCollection(
                processExecutor, stdOutHandler, stdErrorHandler
        );
    }

    public static void userOutput(BuildContext buildContext, String... commands) {
        userOutput(buildContext, Arrays.asList(commands));
    }

    public static void userOutput(BuildContext gradeleExecutionContext, List<String> commands) {
        String commandString = "> " + Strings.listing(commands, " ");
        logger.info(commandString);
        if (gradeleExecutionContext.isVerbose()) System.out.println(commandString);
    }

    private static BuildException createException(ProcessResultCollection result) {
        if (result.isSuccess()) throw new IllegalStateException("Docker process not failed.");
        if (!result.getErrorOut().isEmpty()) {
            return new BuildException(result.getErrorOut().get(0));
        } else {
            return new BuildException();
        }
    }

}
