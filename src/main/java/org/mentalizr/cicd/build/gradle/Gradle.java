package org.mentalizr.cicd.build.gradle;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.BuildProcess;
import org.mentalizr.cicd.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

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

    public static void war(Path projectDir) throws BuildException {
        executeGradleWrapper(projectDir, GradleTask.WAR);
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
        FileUtils.rmDirSilently(projectDir.resolve(".gradle"));
        FileUtils.forceDeleteSilently(projectDir.resolve("gradlew"));
        FileUtils.forceDeleteSilently(projectDir.resolve("gradlew.bat"));
    }

    private static void executeGradleWrapper(Path projectDir, GradleTask gradleTask) throws BuildException {
        assertProjectDir(projectDir);
        assertBuildGradleFile(projectDir);

        BuildProcess.execute(
                projectDir,
                new String[]{getGradleWrapperCommandAbsolute(projectDir), "--console=plain", gradleTask.asLiteral()},
                logger,
                "Gradle build failed."
        );

//        String[] commands = {"bash", "-c", gradleCommand};
    }

    private static void installGradleWrapper(Path projectDir) throws BuildException {
        assertProjectDir(projectDir);
        assertBuildGradleFile(projectDir);
        assertNoGradleWrapper(projectDir);

        BuildProcess.execute(
                projectDir,
                new String[]{"gradle", "--console=plain", "wrapper"},
                logger,
                "Installing gradle wrapper failed."
        );

        BuildProcess.execute(
                projectDir,
                new String[]{getGradleWrapperCommandAbsolute(projectDir), "--version"},
                logger,
                "Installing gradle wrapper failed."
        );
    }

    private static String getGradleWrapperCommandAbsolute(Path projectDir) {
        return projectDir.resolve("gradlew").toAbsolutePath().toString();
    }

    private static void assertProjectDir(Path projectDir) throws BuildException {
        if (!FileUtils.isExistingDirectory(projectDir))
            throw new BuildException("Project directory not found: [" + projectDir.toAbsolutePath() + "].");
    }

    private static void assertBuildGradleFile(Path projectDir) throws BuildException {
        if (!FileHelper.containsFile(projectDir, "build.gradle"))
            throw new BuildException("build.gradle not found in project directory: [" + projectDir.toAbsolutePath() + "].");
    }

    private static void assertNoGradleWrapper(Path projectDir) throws BuildException {
        if (FileHelper.containsFile(projectDir, "gradlew"))
            throw new BuildException("gradle wrapper already installed");
    }

}
