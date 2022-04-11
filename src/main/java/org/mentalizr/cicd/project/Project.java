package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;

import java.nio.file.Path;

public abstract class Project {

    public abstract Path getProjectDir();

    public abstract void prepare();

    public abstract void build() throws TaskExecutionException;

    public abstract void clean();

}
