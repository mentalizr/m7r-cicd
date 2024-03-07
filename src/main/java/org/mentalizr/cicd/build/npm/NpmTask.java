package org.mentalizr.cicd.build.npm;

public enum NpmTask {

    INSTALL("install");

    private final String taskLiteral;

    NpmTask(String taskLiteral) {
        this.taskLiteral = taskLiteral;
    }

    public String asLiteral() {
        return this.taskLiteral;
    }

}
