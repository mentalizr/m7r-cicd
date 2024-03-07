package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.core.collection.Sets;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.npm.Npm;
import org.mentalizr.cicd.build.project.npm.NpmProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Frontend extends NpmProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-frontend");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("m7r-web-components");
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Npm.executeNpmBin(this.getDir(), "webpack");
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void clean() throws TaskExecutionException {
        FileUtils.forceDeleteSilently(this.getDir().resolve("dist"));
    }
    
}
