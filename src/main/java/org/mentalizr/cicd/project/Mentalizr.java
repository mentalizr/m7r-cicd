package org.mentalizr.cicd.project;

import de.arthurpicht.taskRunner.task.TaskExecutionException;
import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.projectModel.Project;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Mentalizr extends Project {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath();
    }

    public String getName() {
        return "m7r";
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet(
                "m7r-frontend",
                "m7r-backend",
                "m7r-infra",
                "content"
                );
    }

    @Override
    public boolean isEntryPoint() {
        return true;
    }

    @Override
    public void init() throws TaskExecutionException {
    }

    @Override
    public void build() throws TaskExecutionException {
    }

    @Override
    public void clean() throws TaskExecutionException {
    }

    @Override
    public void reset() throws TaskExecutionException {
    }

}
