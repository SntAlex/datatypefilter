package org.alexgolikov.cli.model;

import java.util.Collections;
import java.util.Set;

public class ParsedData {
    private final Set<String> inputFiles;
    private final FileWriterConfiguration fileWriterConfiguration;
    private final StatisticsConfiguration statisticsConfiguration;

    public ParsedData(Set<String> inputFiles, FileWriterConfiguration fileWriterConfiguration, StatisticsConfiguration statisticsConfiguration) {
        this.inputFiles = inputFiles;
        this.fileWriterConfiguration = fileWriterConfiguration;
        this.statisticsConfiguration = statisticsConfiguration;
    }

    public Set<String> getInputFiles() {
        return Collections.unmodifiableSet(inputFiles);
    }

    public FileWriterConfiguration getFileWriterConfiguration() {
        return fileWriterConfiguration;
    }

    public StatisticsConfiguration getStatisticsConfiguration() {
        return statisticsConfiguration;
    }
}