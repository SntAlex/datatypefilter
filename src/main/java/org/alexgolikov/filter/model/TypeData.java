package org.alexgolikov.filter.model;

import java.util.Collections;
import java.util.List;

public class TypeData {
    private final List<String> integers;
    private final List<String> doubles;
    private final List<String> strings;

    public TypeData(List<String> integers, List<String> doubles, List<String> strings) {
        this.integers = Collections.unmodifiableList(integers);
        this.doubles = Collections.unmodifiableList(doubles);
        this.strings = Collections.unmodifiableList(strings);
    }

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getDoubles() {
        return doubles;
    }

    public List<String> getStrings() {
        return strings;
    }
}
