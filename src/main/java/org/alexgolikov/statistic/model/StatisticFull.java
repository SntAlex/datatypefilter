package org.alexgolikov.statistic.model;

public class StatisticFull extends StatisticShort {
    private final StatisticFullData statisticFullData;

    public StatisticFull(StatisticFullData statisticFullData, Integer integersCount, Integer doublesCount, Integer stringsCount) {
        super(integersCount, doublesCount, stringsCount);
        this.statisticFullData = statisticFullData;
    }

    public StatisticFullData getStatisticFullData() {
        return statisticFullData;
    }
}