package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.npm.Npm;
import org.mentalizr.cicd.build.project.npm.NpmProject;
import org.mentalizr.commons.paths.build.FrontendDir;
import org.mentalizr.commons.paths.build.WebComponentsDir;

public class Frontend extends NpmProject {

    public Frontend() {
        super(new FrontendDir().asPath());
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Npm.executeNpmBin(this.projectDir, "webpack");
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void clean() throws TaskExecutionException {
        FileUtils.forceDeleteSilently(this.projectDir.resolve("dist"));
    }
    
}
