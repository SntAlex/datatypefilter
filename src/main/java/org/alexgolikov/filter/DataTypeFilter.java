package org.alexgolikov.filter;

import org.alexgolikov.filter.contract.TypeFilterable;
import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceValueResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataTypeFilter implements TypeFilterable {
    @Override
    public ServiceValueResult<TypeData> filterToTypes(List<String> data) {
        Set<String> integers = new HashSet<>();
        Set<String> doubles = new HashSet<>();
        Set<String> strings = new HashSet<>();

        for (String str : data) {
            if (isIntValue(str)) {
                integers.add(str);
            } else if (isDoubleValue(str)) {
                doubles.add(str);
            } else {
                strings.add(str);
            }
        }

        return new ServiceValueResult<>(new TypeData(new ArrayList<>(integers), new ArrayList<>(doubles), new ArrayList<>(strings)));
    }

    private Boolean isIntValue(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isDoubleValue(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}