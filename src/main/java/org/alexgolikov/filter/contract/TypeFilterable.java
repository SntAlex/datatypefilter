package org.alexgolikov.filter.contract;

import org.alexgolikov.resultdata.ServiceValueResult;
import org.alexgolikov.filter.model.TypeData;

import java.util.List;

public interface TypeFilterable {
    ServiceValueResult<TypeData> filterToTypes(List<String> data);
}
