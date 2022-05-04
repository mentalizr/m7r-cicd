package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.gradle.GradleFatJarProject;
import org.mentalizr.cicd.build.project.npm.NpmProject;
import org.mentalizr.commons.paths.build.ContentManagerCliDir;
import org.mentalizr.commons.paths.build.WebComponentsDir;

public class WebComponents extends NpmProject {

    public WebComponents() {
        super(new WebComponentsDir().asPath());
    }

}
