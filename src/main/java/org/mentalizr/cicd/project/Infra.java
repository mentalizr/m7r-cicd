package org.mentalizr.cicd.project;

import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.build.project.gradle.GradleToolJarProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Infra extends GradleToolJarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-infra");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet(
                "m7r-backend-config",
                "m7r-cli",
                "m7r-commons",
                "m7r-persistence-mongo",
                "m7r-persistence-rdbms",
                "m7r-service-objects");
    }

}
