package org.mentalizr.cicd.appInit;

import ch.qos.logback.classic.Level;
import de.arthurpicht.utils.logging.LoggerInit;
import org.mentalizr.commons.paths.host.hostDir.M7rCicdLogFile;

import java.nio.file.Path;

public class AppInit {

    public static void execute() {
        initLogging();

    }

    private static void initLogging() {
        M7rCicdLogFile m7rCicdLogFile = M7rCicdLogFile.createInstance();
        Path logFile = m7rCicdLogFile.asPath().resolve("m7r-cicd.log");
        LoggerInit.consoleAndFile(logFile, Level.DEBUG, Level.OFF);
    }

}
