package org.mentalizr.cicd.build.npm;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.BuildProcess;
import org.mentalizr.cicd.build.gradle.GradleTask;
import org.mentalizr.cicd.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Npm {

    private static final Logger logger = LoggerFactory.getLogger("npm");

    public static void install(Path projectDir) throws BuildException {
        executeNpm(projectDir, NpmTask.INSTALL);
    }

    public static void reset(Path projectDir) {
        FileUtils.forceDeleteSilently(projectDir.resolve("node-modules"));
    }

    private static void executeNpm(Path projectDir, NpmTask npmTask) throws BuildException {
        assertProjectDir(projectDir);
        assertPackageJsonFile(projectDir);

        BuildProcess.execute(
                projectDir,
                new String[]{"npm", "--no-color", "--no-progress", npmTask.asLiteral()},
                logger,
                "npm call failed."
        );
    }

    public static void executeNpmBin(Path projectDir, String command) throws BuildException {
        assertProjectDir(projectDir);
        assertPackageJsonFile(projectDir);

        String npmBinCommandAbsolute =
                projectDir.resolve("node_modules").resolve(".bin").resolve(command)
                        .toAbsolutePath().toString();
        BuildProcess.execute(
                projectDir,
                new String[]{npmBinCommandAbsolute},
                logger,
                "npm local .bin call failed: " + npmBinCommandAbsolute
        );
    }

    private static void assertProjectDir(Path projectDir) throws BuildException {
        if (!FileUtils.isExistingDirectory(projectDir))
            throw new BuildException("Project directory not found: [" + projectDir.toAbsolutePath() + "].");
    }

    private static void assertPackageJsonFile(Path projectDir) throws BuildException {
        if (!FileHelper.containsFile(projectDir, "package.json"))
            throw new BuildException("package.json not found in project directory: [" + projectDir.toAbsolutePath() + "].");
    }

    private static void assertNoNodeModules(Path projectDir) throws BuildException {
        if (FileHelper.containsDirectory(projectDir, "node_modules"))
            throw new BuildException("npm project already installed");
    }

}
