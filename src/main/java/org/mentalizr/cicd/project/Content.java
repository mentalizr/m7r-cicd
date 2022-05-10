package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.build.BuildException;
import org.mentalizr.cicd.build.CommandLineTool;
import org.mentalizr.cicd.projectModel.Project;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Content extends Project {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("content");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("m7r-content-manager-cli");
    }

    @Override
    public void init() throws TaskExecutionException {}

    @Override
    public void build() throws TaskExecutionException {
        try {
            CommandLineTool.execute(getDir(), new String[]{"m7r-cm", "build"});
        } catch (BuildException e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }

    @Override
    public void clean() throws TaskExecutionException {
    }

    @Override
    public void reset() throws TaskExecutionException {
    }

}
