package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.BackendConfigDir;

public class BackendConfig extends GradleJarProject {

    public BackendConfig() {
        super(new BackendConfigDir().asPath());
    }

}
