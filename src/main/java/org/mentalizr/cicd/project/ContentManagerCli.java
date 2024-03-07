package org.mentalizr.cicd.project;

import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.build.project.gradle.GradleFatJarProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class ContentManagerCli extends GradleFatJarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-content-manager-cli");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("m7r-service-objects", "m7r-mdp-compiler", "m7r-content-manager");
    }

}
