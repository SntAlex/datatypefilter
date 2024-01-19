package org.alexgolikov.statistics.model;

public class StatisticsFullData {
    private final String minimalValue;
    private final String maximumValue;
    private final String averageValue;
    private final String sumValue;
    private final Integer shortestStringSize;
    private final Integer longestStringSize;


    public StatisticsFullData(String minimalValue, String maximumValue, String averageValue, String sumValue, Integer shortestStringSize, Integer longestStringSize) {
        this.minimalValue = minimalValue;
        this.maximumValue = maximumValue;
        this.averageValue = averageValue;
        this.sumValue = sumValue;
        this.shortestStringSize = shortestStringSize;
        this.longestStringSize = longestStringSize;
    }

    public String getMinimalValue() {
        return minimalValue;
    }

    public String getMaximumValue() {
        return maximumValue;
    }

    public String getAverageValue() {
        return averageValue;
    }

    public String getSumValue() {
        return sumValue;
    }

    public Integer getShortestStringSize() {
        return shortestStringSize;
    }

    public Integer getLongestStringSize() {
        return longestStringSize;
    }
}
