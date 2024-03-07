package org.mentalizr.cicd.project;

import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.build.project.gradle.GradleWarProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Backend extends GradleWarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-backend");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet(
                "m7r-frontend",
                "m7r-commons",
                "m7r-backend-config",
                "m7r-backend-proc",
                "m7r-persistence-mongo",
                "m7r-persistence-rdbms");
    }

}
