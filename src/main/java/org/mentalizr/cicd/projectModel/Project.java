package org.mentalizr.cicd.projectModel;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.struct.dag.manager.DagElement;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

public abstract class Project implements DagElement {

    protected final String name;
    protected final Path projectDir;
    protected final Set<String> dependencies;
    protected final boolean isBuildTarget;

    public Project(String name, Path projectDir, Set<String> dependencies, boolean isBuildTarget) {
        this.name = name;
        this.projectDir = projectDir;
        this.dependencies = dependencies;
        this.isBuildTarget = isBuildTarget;
    }

    @Override
    public String getId() {
        return this.name;
    }

    @Override
    public Set<String> getDependencies() {
        return this.dependencies;
    }

    @Override
    public boolean isEntryPoint() {
        return this.isBuildTarget;
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
        return this.name.equals(project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
