package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.CommonsDir;
import org.mentalizr.commons.paths.host.GitReposDir;

import java.nio.file.Path;

public class Commons extends GradleJarProject {

    @Override
    public Path getDir() {
        return GitReposDir.createInstance().asPath().resolve("core/m7r-commons");
    }

}
