package org.mentalizr.cicd.build.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;
import org.mentalizr.cicd.build.project.GradleProject;
import org.mentalizr.commons.paths.build.ContentManagerCliDir;

import java.nio.file.Path;

public abstract class GradleFatJarProject extends GradleProject {

    public GradleFatJarProject(Path projectDir) {
        super(projectDir);
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Gradle.fatJar(this.getProjectDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

}
