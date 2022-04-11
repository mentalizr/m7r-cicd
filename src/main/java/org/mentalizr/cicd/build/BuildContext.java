package org.mentalizr.cicd.build;

import org.slf4j.Logger;

public class BuildContext {

    private final boolean verbose;
    private final Logger logger;

    public BuildContext(boolean verbose, Logger logger) {
        this.verbose = verbose;
        this.logger = logger;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public Logger getLogger() {
        return logger;
    }
}
