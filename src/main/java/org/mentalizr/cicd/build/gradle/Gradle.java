package org.mentalizr.cicd.build.gradle;

import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessExecutor;
import de.arthurpicht.processExecutor.ProcessExecutorBuilder;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardErrorHandler;
import de.arthurpicht.processExecutor.outputHandler.generalOutputHandler.GeneralStandardOutHandler;
import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.ExecutionContext;
import org.mentalizr.cicd.build.BuildContext;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Gradle {

    private static final Logger logger = LoggerFactory.getLogger("gradle");

    public static void build(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.BUILD);
    }

    public static void jar(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.JAR);
    }

    public static void fatJar(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.FATJAR);
    }

    public static void extractDependencies(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.EXTRACT_DEPENDENCIES);
    }

    public static void clean(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.CLEAN);
    }

    public static void init(Path projectDir) throws BuildException {
        installGradleWrapper(projectDir);
    }

    public static void reset(Path projectDir) {
        FileUtils.rmDirSilently(projectDir.resolve("build"));
        FileUtils.rmDirSilently(projectDir.resolve("gradle"));
        FileUtils.forceDeleteSilently(projectDir.resolve("gradlew"));
        FileUtils.forceDeleteSilently(projectDir.resolve("gradlew.bat"));
    }

    private static void executeGradleWrapper(Path projectDir, GradleTask gradleTask) throws BuildException {
        if (!FileUtils.isExistingDirectory(projectDir))
            throw new BuildException("Project directory not found: [" + projectDir.toAbsolutePath() + "].");
        if (!FileHelper.containsFile(projectDir, "build.gradle"))
            throw new BuildException("build.gradle not found in project directory: [" + projectDir.toAbsolutePath() + "].");

        BuildContext buildContext = ExecutionContext.getGradleExecutionContext();

        String gradleCommand = projectDir.resolve("gradlew").toAbsolutePath().toString();
        gradleCommand += " --console=plain " + gradleTask.asLiteral();
        String[] commands = {"bash", "-c", gradleCommand};

        userOutput(buildContext, commands);
        ProcessResultCollection result;

        try{
            result = executeProcess(
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

    private static void installGradleWrapper(Path projectDir) throws BuildException {
        if (!FileUtils.isExistingDirectory(projectDir))
            throw new BuildException("Project directory not found: [" + projectDir.toAbsolutePath() + "].");
        if (!FileHelper.containsFile(projectDir, "build.gradle"))
            throw new BuildException("build.gradle not found in project directory: [" + projectDir.toAbsolutePath() + "].");
        if (FileHelper.containsFile(projectDir, "gradlew"))
            throw new BuildException("gradle wrapper already installed");

        BuildContext buildContext = ExecutionContext.getGradleExecutionContext();

        String[] commandWrapper = {"gradle", "--console=plain", "wrapper"};
        userOutput(buildContext, commandWrapper);
        ProcessResultCollection result;
        try{
            result = executeProcess(
                    projectDir,
                    buildContext.getLogger(),
                    buildContext.isVerbose(),
                    commandWrapper
            );
        } catch (ProcessExecutionException e) {
            throw new BuildException("Installing gradle wrapper failed: " + e.getMessage(), e);
        }
        if (result.isFail()) throw createException(result);

        String[] commandVersion = {"gradle", "--version"};
        userOutput(buildContext, commandVersion);
        try{
            result = executeProcess(
                    projectDir,
                    buildContext.getLogger(),
                    buildContext.isVerbose(),
                    commandVersion
            );
        } catch (ProcessExecutionException e) {
            throw new BuildException("Installing gradle wrapper failed: " + e.getMessage(), e);
        }
        if (result.isFail()) throw createException(result);
    }


    private static ProcessResultCollection executeProcess(Path projectDir, Logger logger, boolean verbose, String... commands) throws ProcessExecutionException {
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

    public static void userOutput(BuildContext buildContext, List<String> commands) {
        String commandString = "> " + Strings.listing(commands, " ");
        logger.info(commandString);
        if (buildContext.isVerbose()) System.out.println(commandString);
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
