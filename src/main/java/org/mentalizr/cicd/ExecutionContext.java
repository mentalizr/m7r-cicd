package org.mentalizr.cicd;

import de.arthurpicht.cli.CliCall;
import org.mentalizr.cicd.build.BuildContext;
import org.slf4j.LoggerFactory;

public class ExecutionContext {

    private static BuildContext buildContext = null;

    public static void initialize(CliCall cliCall) {
        buildContext = new BuildContext(false, LoggerFactory.getLogger("gradle"));
    }

    public static BuildContext getGradleExecutionContext() {
        if (buildContext == null)
            throw new IllegalStateException(ExecutionContext.class.getSimpleName() + " not initialized yet.");
        return buildContext;
    }

}
