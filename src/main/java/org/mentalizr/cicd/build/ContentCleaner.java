package org.mentalizr.cicd.build;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ContentCleaner {

    public static void clean(Path contentDir) throws BuildException {
        try {
            List<Path> programDirs = FileUtils.getSubdirectoriesNotEndingWithTilde(contentDir);
            for (Path programDir : programDirs) {
                Path htmlDir = programDir.resolve("html");
                FileUtils.forceDeleteSilently(htmlDir);
            }
        } catch (IOException e) {
            throw new BuildException(e.getMessage(), e);
        }
    }

}
