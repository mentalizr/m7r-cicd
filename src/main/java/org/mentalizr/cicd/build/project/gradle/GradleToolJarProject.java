package org.mentalizr.cicd.build.project.gradle;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;

public abstract class GradleToolJarProject extends GradleProject {

    @Override
    public void build() throws TaskExecutionException {
        try {
            Gradle.extractDependencies(this.getDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

}
