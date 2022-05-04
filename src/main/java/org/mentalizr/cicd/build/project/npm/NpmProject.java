package org.mentalizr.cicd.build.project.npm;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;
import org.mentalizr.cicd.build.npm.Npm;
import org.mentalizr.cicd.build.project.Project;
import org.mentalizr.cicd.utils.FileHelper;

import java.nio.file.Path;

public abstract class NpmProject extends Project {

    public NpmProject(Path projectDir) {
        super(projectDir);
    }

    @Override
    public void init() throws TaskExecutionException {
        try {
            Npm.install(this.projectDir);
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isInitialized() {
        return FileHelper.containsDirectory(this.projectDir, "node_modules");
    }

    @Override
    public void clean() throws TaskExecutionException {
        // din
    }

    @Override
    public void build() {
        // TODO
    }

    @Override
    public void reset() {
        Npm.reset(this.projectDir);
    }

}
