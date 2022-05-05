package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleWarProject;
import org.mentalizr.commons.paths.build.BackendDir;

public class Backend extends GradleWarProject {

    public Backend() {
        super(new BackendDir().asPath());
    }

}
