package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.gradle.Gradle;
import org.mentalizr.commons.paths.build.ContentManagerCliDir;

import java.nio.file.Path;

public class ContentManagerCli extends Project {

    @Override
    public Path getProjectDir() {
        return new ContentManagerCliDir().asPath();
    }

    @Override
    public void prepare() {
        throw new RuntimeException("NIY");
    }

    @Override
    public void build() throws TaskExecutionException {
        try {
            Gradle.build(this.getProjectDir());
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void clean() {
        throw new RuntimeException("NIY");
    }
}
