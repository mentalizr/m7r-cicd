package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;

public class Provisioning extends GradleJarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("infra/m7r-provisioning");
    }

}
