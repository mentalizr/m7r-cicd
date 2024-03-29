package org.mentalizr.cicd.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.processExecutor.ProcessExecution;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.CicdCli;
import org.mentalizr.cicd.ExecutionContext;
import org.mentalizr.commons.paths.host.hostDir.M7rCicdLogFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogsExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(LogsExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Show logs ...");

        Path logFile = new M7rCicdLogFile().asPath();
        if (!FileUtils.isExistingRegularFile(logFile))
            throw new CommandExecutorException("Log file not found: [" + logFile.toAbsolutePath() + "].");

        boolean follow = cliCall.getOptionParserResultSpecific().hasOption(CicdCli.SPECIFIC_OPTION_FOLLOW);

        showLogs(logFile, follow);
    }

    public void showLogs(Path file, boolean follow) throws CommandExecutorException {
        List<String> commands = new ArrayList<>();
        commands.add("tail");
        if (follow) {
            commands.add("-f");
        }
        commands.add(file.toAbsolutePath().toString());

        logger.info("execute >>> " + Strings.listing(commands, " "));
        try {
            ProcessExecution.executeInteractive(commands);
        } catch (ProcessExecutionException e) {
            throw new CommandExecutorException("Exception on showing logs. " + e.getMessage(), e);
        }
    }

}
