package org.mentalizr.cicd.projectModel;

public class ProjectModelBuilderRuntimeException extends RuntimeException {

    public ProjectModelBuilderRuntimeException() {
    }

    public ProjectModelBuilderRuntimeException(String message) {
        super(message);
    }

    public ProjectModelBuilderRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectModelBuilderRuntimeException(Throwable cause) {
        super(cause);
    }

    public ProjectModelBuilderRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
