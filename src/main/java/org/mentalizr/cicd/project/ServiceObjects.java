package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.ServiceObjectsDir;

public class ServiceObjects extends GradleJarProject {

    public ServiceObjects() {
        super(new ServiceObjectsDir().asPath());
    }

}
