package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.GradleFatJarProject;
import org.mentalizr.commons.paths.build.ContentManagerCliDir;

public class ContentManagerCli extends GradleFatJarProject {

    public ContentManagerCli() {
        super(new ContentManagerCliDir().asPath());
    }

}
