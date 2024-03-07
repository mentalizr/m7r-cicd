package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.npm.Npm;
import org.mentalizr.cicd.build.project.npm.NpmProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;

public class WebComponents extends NpmProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-web-components");
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Npm.executeNpmBin(this.getDir(), "tsc");
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void clean() throws TaskExecutionException {
        FileUtils.forceDeleteSilently(this.getDir().resolve("dist"));
    }

}
