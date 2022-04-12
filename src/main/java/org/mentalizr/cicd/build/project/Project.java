package org.mentalizr.cicd.build.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;

import java.nio.file.Path;
import java.util.Objects;

public abstract class Project {

    protected final Path projectDir;

    public Project(Path projectDir) {
        this.projectDir = projectDir;
    }

    public Path getProjectDir() {
        return this.projectDir;
    }

    public String getName() {
        return this.projectDir.getFileName().toString();
    }

    public boolean isInitialized() {
        return false;
    }

    public abstract void init() throws TaskExecutionException;

    public abstract void build() throws TaskExecutionException;

    public abstract void clean() throws TaskExecutionException;

    public abstract void reset() throws TaskExecutionException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectDir.equals(project.projectDir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectDir);
    }

}
