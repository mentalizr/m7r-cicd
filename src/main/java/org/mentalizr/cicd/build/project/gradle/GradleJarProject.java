package org.mentalizr.cicd.build.project.gradle;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;

public abstract class GradleJarProject extends GradleProject {

    @Override
    public void build() throws TaskExecutionException {
        try {
            Gradle.jar(this.getDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

}
