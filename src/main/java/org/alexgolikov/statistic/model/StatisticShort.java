package org.alexgolikov.statistic.model;

public class StatisticShort {
    private final Integer integersCount;
    private final Integer doublesCount;
    private final Integer stringsCount;

    public StatisticShort(Integer integersCount, Integer doublesCount, Integer stringsCount) {
        this.integersCount = integersCount;
        this.doublesCount = doublesCount;
        this.stringsCount = stringsCount;
    }

    public Integer getIntegersCount() {
        return integersCount;
    }

    public Integer getDoublesCount() {
        return doublesCount;
    }

    public Integer getStringsCount() {
        return stringsCount;
    }
}
