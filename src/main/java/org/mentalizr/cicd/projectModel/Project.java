package org.mentalizr.cicd.projectModel;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.struct.dag.manager.DagElement;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Project implements DagElement {

    public abstract Path getDir();

    public String getName() {
        return this.getDir().getFileName().toString();
    }

    @Override
    public String getId() {
        return getName();
    }

    @Override
    public Set<String> getDependencies() {
        return new HashSet<>();
    }

    @Override
    public boolean isEntryPoint() {
        return false;
    }

    public abstract void init() throws TaskExecutionException;

    public boolean isInitialized() {
        return false;
    }

    public abstract void build() throws TaskExecutionException;

    public abstract void clean() throws TaskExecutionException;

    public abstract void reset() throws TaskExecutionException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return getName().equals(project.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

}
