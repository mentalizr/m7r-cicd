package org.mentalizr.cicd.project;

import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.cicd.build.project.gradle.GradleToolJarProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;
import java.util.Set;

public class Provisioning extends GradleToolJarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("infra/m7r-provisioning");
    }

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("m7r-commons");
    }

}
