package org.alexgolikov.statistics;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.alexgolikov.statistics.contract.Statistical;
import org.alexgolikov.statistics.model.StatisticsFull;
import org.alexgolikov.statistics.model.StatisticsFullData;
import org.alexgolikov.statistics.model.StatisticsShort;
import org.alexgolikov.statistics.model.StatisticsValuePair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsHandler implements Statistical {
    @Override
    public ServiceValueResult<StatisticsShort> analyse(Boolean isFullData, TypeData typeData) {
        Integer integersCount = typeData.getIntegers().size();
        Integer stringsCount = typeData.getStrings().size();
        Integer doublesCount = typeData.getDoubles().size();

        if (!isFullData) {
            return new ServiceValueResult<>(new StatisticsShort(integersCount, stringsCount, doublesCount));
        }

        List<String> allNumberStrings = Stream.of(typeData.getIntegers(), typeData.getDoubles()).flatMap(Collection::stream).collect(Collectors.toList());
        String minimalNumber = allNumberStrings.stream().map(x -> new StatisticsValuePair<>(Double.parseDouble(x), x)).min(Comparator.comparing(StatisticsValuePair::getValue)).map(StatisticsValuePair::getStringValue).orElse("");
        String maximumNumber = allNumberStrings.stream().map(x -> new StatisticsValuePair<>(Double.parseDouble(x), x)).max(Comparator.comparing(StatisticsValuePair::getValue)).map(StatisticsValuePair::getStringValue).orElse("");
        String sum = Double.toString(allNumberStrings.stream().mapToDouble(Double::parseDouble).sum());
        OptionalDouble avgOptional = allNumberStrings.stream().mapToDouble(Double::parseDouble).average();
        String avg = avgOptional.isPresent() ? Double.toString(avgOptional.getAsDouble()) : "0";

        Optional<Integer> shortestStringSize = typeData.getStrings().stream().map(String::length).min(Integer::compare);
        Optional<Integer> longestStringSize = typeData.getStrings().stream().map(String::length).max(Integer::compare);

        StatisticsFullData statisticsFullData = new StatisticsFullData(minimalNumber, maximumNumber, sum, avg, shortestStringSize.orElse(0), longestStringSize.orElse(0));

        return new ServiceValueResult<>(new StatisticsFull(statisticsFullData, integersCount, doublesCount, stringsCount));
    }
}