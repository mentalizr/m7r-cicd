package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.PersistenceMongoDir;

public class PersistenceMongo extends GradleJarProject {

    public PersistenceMongo() {
        super(new PersistenceMongoDir().asPath());
    }

}
