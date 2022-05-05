package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleJarProject;
import org.mentalizr.commons.paths.build.CommonsDir;

public class Commons extends GradleJarProject {

    public Commons() {
        super(new CommonsDir().asPath());
    }

}
