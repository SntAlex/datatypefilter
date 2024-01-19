package org.alexgolikov.cli.model;

public class StatisticsConfiguration {
    private final Boolean fullData;
    private final Boolean statisticEnabled;

    public StatisticsConfiguration() {
        statisticEnabled = false;
        fullData = false;
    }

    public StatisticsConfiguration(Boolean fullData) {
        this.statisticEnabled = true;
        this.fullData = fullData;
    }

    public Boolean isStatisticEnabled() {
        return statisticEnabled;
    }

    public Boolean isFullData() {
        return fullData;
    }
}
