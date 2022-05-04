package org.mentalizr.cicd.build;

import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.cicd.ExecutionContext;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class BuildProcess {

    public static void execute(Path projectDir, String[] command, Logger logger, String errorMessage) throws BuildException {
        BuildContext buildContext = ExecutionContext.getGradleExecutionContext();

        userOutput(buildContext, Arrays.asList(command), logger);
        ProcessResultCollection result;
        try{
            result = Process.execute(
                    projectDir,
                    buildContext.getLogger(),
                    buildContext.isVerbose(),
                    command
            );
        } catch (ProcessExecutionException e) {
            throw new BuildException(errorMessage + " " + e.getMessage(), e);
        }
        if (result.isFail()) throw createException(result);
    }

    private static BuildException createException(ProcessResultCollection result) {
        if (result.isSuccess()) throw new IllegalStateException("Build process did not fail.");
        if (!result.getErrorOut().isEmpty()) {
            return new BuildException(result.getErrorOut().get(0));
        } else {
            return new BuildException();
        }
    }

    public static void userOutput(BuildContext buildContext, List<String> command, Logger logger) {
        String commandString = "> " + Strings.listing(command, " ");
        logger.info(commandString);
        if (buildContext.isVerbose()) System.out.println(commandString);
    }

}
