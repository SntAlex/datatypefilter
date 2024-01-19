package org.alexgolikov.statistics.model;

public class StatisticsFull extends StatisticsShort {
    private final StatisticsFullData statisticsFullData;

    public StatisticsFull(StatisticsFullData statisticsFullData, Integer integersCount, Integer doublesCount, Integer stringsCount) {
        super(integersCount, doublesCount, stringsCount);
        this.statisticsFullData = statisticsFullData;
    }

    public StatisticsFullData getStatisticFullData() {
        return statisticsFullData;
    }
}