package org.mentalizr.cicd.projectModel;

import de.arthurpicht.utils.struct.dag.manager.DagManager;
import de.arthurpicht.utils.struct.dag.manager.DagManagerBuilder;
import de.arthurpicht.utils.struct.dag.manager.DagValidationException;

public class ProjectModelBuilder {

    private final DagManagerBuilder dagManagerBuilder;

    public ProjectModelBuilder() {
        this.dagManagerBuilder = new DagManagerBuilder();
    }

    public ProjectModelBuilder withProject(Project project) {
        try {
            this.dagManagerBuilder.withDagElement(project);
        } catch (DagValidationException e) {
            throw new ProjectModelBuilderRuntimeException(
                    "Unable to add project [" + project.getId() + "] to " + ProjectModelBuilder.class.getSimpleName()
                            + ". " + e.getMessage(), e);
        }
        return this;
    }

    public ProjectModel build() {
        DagManager dagManager;
        try {
            dagManager = this.dagManagerBuilder.build();
        } catch (DagValidationException e) {
            throw new ProjectModelBuilderRuntimeException(
                    "Unable to build " + ProjectModelBuilder.class.getSimpleName() + ". " + e.getMessage(), e);
        }
        return new ProjectModel(dagManager);
    }

}
