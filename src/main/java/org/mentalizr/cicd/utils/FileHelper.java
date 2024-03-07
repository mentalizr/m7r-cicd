package org.mentalizr.cicd.utils;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;
import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNullAndNotEmpty;

public class FileHelper {

    /**
     * Checks if specified directory contains a regular file with specified name.
     *
     * @param dir existing directory
     * @param filename filename to check for
     * @return true if regular file exists in directory, else false.
     */
    public static boolean containsFile(Path dir, String filename) {
        assertArgumentNotNull("dir", dir);
        assertArgumentNotNull("filename", filename);
        if (filename.isEmpty())
            throw new IllegalArgumentException("Specified file name is empty.");
        if (!FileUtils.isExistingDirectory(dir))
            throw new IllegalArgumentException("Specified directory not found: [" + dir.toAbsolutePath() + "].");
        Path file = Paths.get(filename);
        if (file.getNameCount() != 1)
            throw new IllegalArgumentException("Specified file name contains path elements: [" + file + "].");
        Path filePath = dir.resolve(file);
        return FileUtils.isExistingRegularFile(filePath);
    }

    public static boolean containsDirectory(Path dir, String dirname) {
        assertArgumentNotNull("dir", dir);
        assertArgumentNotNullAndNotEmpty("dirname", dirname);
        if (!FileUtils.isExistingDirectory(dir))
            throw new IllegalArgumentException("Specified directory not found: [" + dir.toAbsolutePath() + "].");
        Path subDir = dir.resolve(dirname);
        return (FileUtils.isExistingDirectory(subDir));
    }

}
