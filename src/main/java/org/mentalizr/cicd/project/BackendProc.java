package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.BackendProcDir;

public class BackendProc extends GradleJarProject {

    public BackendProc() {
        super(new BackendProcDir().asPath());
    }

}
