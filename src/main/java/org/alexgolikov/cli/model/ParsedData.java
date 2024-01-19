package org.alexgolikov.cli.model;

import java.util.Collections;
import java.util.Set;

public class ParsedData {
    private final Set<String> inputFiles;
    private final FileWriterConfiguration fileWriterConfiguration;
    private final StatisticConfiguration statisticConfiguration;

    public ParsedData(Set<String> inputFiles, FileWriterConfiguration fileWriterConfiguration, StatisticConfiguration statisticConfiguration) {
        this.inputFiles = inputFiles;
        this.fileWriterConfiguration = fileWriterConfiguration;
        this.statisticConfiguration = statisticConfiguration;
    }

    public Set<String> getInputFiles() {
        return Collections.unmodifiableSet(inputFiles);
    }

    public FileWriterConfiguration getFileWriterConfiguration() {
        return fileWriterConfiguration;
    }

    public StatisticConfiguration getStatisticConfiguration() {
        return statisticConfiguration;
    }
}