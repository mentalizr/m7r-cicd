package org.mentalizr.cicd.build.project.gradle;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;

import java.nio.file.Path;

public abstract class GradleWarProject extends GradleProject {

    public GradleWarProject(Path projectDir) {
        super(projectDir);
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Gradle.war(this.getProjectDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

}
