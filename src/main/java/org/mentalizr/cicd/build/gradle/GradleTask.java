package org.mentalizr.cicd.build.gradle;

public enum GradleTask {

    BUILD("build"),
    JAR("jar"),
    FATJAR("fatJar"),
    EXTRACT_DEPENDENCIES("extractDependencies"),
    CLEAN("clean"),
    WAR("war");

    private final String gradleTaskLiteral;

    GradleTask(String gradleTaskLiteral) {
        this.gradleTaskLiteral = gradleTaskLiteral;
    }

    public String asLiteral() {
        return this.gradleTaskLiteral;
    }

}
