package org.alexgolikov.statistic;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.resultdata.ServiceValueResult;
import org.alexgolikov.statistic.contract.Statistical;
import org.alexgolikov.statistic.model.StatisticFull;
import org.alexgolikov.statistic.model.StatisticFullData;
import org.alexgolikov.statistic.model.StatisticShort;
import org.alexgolikov.statistic.model.StatisticValuePair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticHandler implements Statistical {
    @Override
    public ServiceValueResult<StatisticShort> analyse(Boolean isFullData, TypeData typeData) {
        Integer integersCount = typeData.getIntegers().size();
        Integer stringsCount = typeData.getStrings().size();
        Integer doublesCount = typeData.getDoubles().size();

        if (!isFullData) {
            return new ServiceValueResult<>(new StatisticShort(integersCount, stringsCount, doublesCount));
        }

        List<String> allNumberStrings = Stream.of(typeData.getIntegers(), typeData.getDoubles()).flatMap(Collection::stream).collect(Collectors.toList());
        String minimalNumber = allNumberStrings.stream().map(x -> new StatisticValuePair<>(Double.parseDouble(x), x)).min(Comparator.comparing(StatisticValuePair::getValue)).map(StatisticValuePair::getStringValue).orElse("");
        String maximumNumber = allNumberStrings.stream().map(x -> new StatisticValuePair<>(Double.parseDouble(x), x)).max(Comparator.comparing(StatisticValuePair::getValue)).map(StatisticValuePair::getStringValue).orElse("");
        String sum = Double.toString(allNumberStrings.stream().mapToDouble(Double::parseDouble).sum());
        OptionalDouble avgOptional = allNumberStrings.stream().mapToDouble(Double::parseDouble).average();
        String avg = avgOptional.isPresent() ? Double.toString(avgOptional.getAsDouble()) : "";

        Optional<Integer> shortestStringSize = typeData.getStrings().stream().map(String::length).min(Integer::compare);
        Optional<Integer> longestStringSize = typeData.getStrings().stream().map(String::length).max(Integer::compare);

        StatisticFullData statisticFullData = new StatisticFullData(minimalNumber, maximumNumber, sum, avg, shortestStringSize.orElse(0), longestStringSize.orElse(0));

        return new ServiceValueResult<>(new StatisticFull(statisticFullData, integersCount, doublesCount, stringsCount));
    }
}