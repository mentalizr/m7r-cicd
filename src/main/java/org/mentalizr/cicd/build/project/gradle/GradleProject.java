package org.mentalizr.cicd.build.project.gradle;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;
import org.mentalizr.cicd.projectModel.Project;
import org.mentalizr.cicd.utils.FileHelper;

public abstract class GradleProject extends Project {

    @Override
    public void init() throws TaskExecutionException {
        try {
            Gradle.init(this.getDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isInitialized() {
        return FileHelper.containsFile(this.getDir(), "gradlew");
    }

    @Override
    public void clean() throws TaskExecutionException {
        try {
            Gradle.clean(this.getDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void reset() throws TaskExecutionException {
        Gradle.reset(this.getDir());
    }

}
