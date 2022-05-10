package org.mentalizr.cicd.build.project.npm;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.npm.Npm;
import org.mentalizr.cicd.projectModel.Project;
import org.mentalizr.cicd.utils.FileHelper;

import java.nio.file.Path;

public abstract class NpmProject extends Project {

    @Override
    public void init() throws TaskExecutionException {
        try {
            Npm.install(this.getDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isInitialized() {
        return FileHelper.containsDirectory(this.getDir(), "node_modules");
    }

    @Override
    public void reset() {
        Npm.reset(this.getDir());
    }

}
