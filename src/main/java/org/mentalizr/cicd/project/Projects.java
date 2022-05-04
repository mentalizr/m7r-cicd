package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.ProjectRegistry;

public class Projects {

    public static ProjectRegistry create() {

        ProjectRegistry projectRegistry = new ProjectRegistry();
        projectRegistry.add(new ContentManagerCli());
        projectRegistry.add(new WebComponents());

        return projectRegistry;
    }

}
