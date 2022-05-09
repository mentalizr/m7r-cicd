package org.mentalizr.cicd.build.project.gradle;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;
import org.mentalizr.cicd.projectModel.Project;
import org.mentalizr.cicd.utils.FileHelper;

import java.nio.file.Path;

public abstract class GradleProject extends Project {

    public GradleProject(Path projectDir) {
        super(projectDir);
    }

    @Override
    public void init() throws TaskExecutionException {
        try {
            Gradle.init(this.projectDir);
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isInitialized() {
        return FileHelper.containsFile(this.projectDir, "gradlew");
    }

    @Override
    public void clean() throws TaskExecutionException {
        try {
            Gradle.clean(this.projectDir);
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void reset() throws TaskExecutionException {
        Gradle.reset(this.projectDir);
    }

}
