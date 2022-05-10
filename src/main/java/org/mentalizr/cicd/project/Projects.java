package org.mentalizr.cicd.project;

import org.mentalizr.cicd.projectModel.ProjectModel;
import org.mentalizr.cicd.projectModel.ProjectModelBuilder;

public class Projects {

    public static ProjectModel create() {

        return new ProjectModelBuilder()
                .withProject(new Mentalizr())
                .withProject(new Commons())
                .withProject(new BackendConfig())
                .withProject(new BackendProc())
                .withProject(new PersistenceMongo())
                .withProject(new PersistenceRdbms())
                .withProject(new ServiceObjects())
                .withProject(new MdpCompiler())
                .withProject(new ContentManager())
                .withProject(new ContentManagerCli())
                .withProject(new WebComponents())
                .withProject(new Frontend())
                .withProject(new Backend())
                .withProject(new Cli())
                .withProject(new Infra())
                .withProject(new Content())
                .build();

//        ProjectRegistry projectRegistry = new ProjectRegistry();
//        projectRegistry.add(new Commons());
//        projectRegistry.add(new BackendConfig());
//        projectRegistry.add(new BackendProc());
//        projectRegistry.add(new PersistenceMongo());
//        projectRegistry.add(new PersistenceRdbms());
//        projectRegistry.add(new ServiceObjects());
//        projectRegistry.add(new ContentManagerCli());
//        projectRegistry.add(new WebComponents());
//        projectRegistry.add(new Frontend());
//        projectRegistry.add(new Backend());
//
//        return projectRegistry;
    }

}
