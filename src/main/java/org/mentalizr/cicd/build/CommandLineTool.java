package org.mentalizr.cicd.build;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.BuildProcess;
import org.mentalizr.cicd.build.npm.NpmTask;
import org.mentalizr.cicd.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class CommandLineTool {

    private static final Logger logger = LoggerFactory.getLogger("commandLineTool");

    public static void execute(Path projectDir, String[] toolCall) throws BuildException {
        assertProjectDir(projectDir);

        BuildProcess.execute(
                projectDir,
                toolCall,
                logger,
                "tool call failed."
        );
    }

    private static void assertProjectDir(Path projectDir) throws BuildException {
        if (!FileUtils.isExistingDirectory(projectDir))
            throw new BuildException("Project directory not found: [" + projectDir.toAbsolutePath() + "].");
    }

}
