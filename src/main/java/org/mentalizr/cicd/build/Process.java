package org.mentalizr.cicd.build;

import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessExecutor;
import de.arthurpicht.processExecutor.ProcessExecutorBuilder;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardErrorHandler;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardOutHandler;
import org.slf4j.Logger;

import java.nio.file.Path;

public class Process {

    public static ProcessResultCollection execute(Path projectDir, Logger logger, boolean verbose, String... commands) throws ProcessExecutionException {
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

}
