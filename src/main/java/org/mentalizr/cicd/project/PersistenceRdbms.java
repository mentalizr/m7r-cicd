package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.PersistenceRdbmsDir;

public class PersistenceRdbms extends GradleJarProject {

    public PersistenceRdbms() {
        super(new PersistenceRdbmsDir().asPath());
    }

}
