package org.mentalizr.cicd.project;

import org.mentalizr.cicd.build.project.ProjectRegistry;

public class Projects {

    public static ProjectRegistry create() {

        ProjectRegistry projectRegistry = new ProjectRegistry();
        projectRegistry.add(new Commons());
        projectRegistry.add(new BackendConfig());
        projectRegistry.add(new BackendProc());
        projectRegistry.add(new PersistenceMongo());
        projectRegistry.add(new PersistenceRdbms());
        projectRegistry.add(new ServiceObjects());
        projectRegistry.add(new ContentManagerCli());
        projectRegistry.add(new WebComponents());
        projectRegistry.add(new Frontend());
        projectRegistry.add(new Backend());

        return projectRegistry;
    }

}
