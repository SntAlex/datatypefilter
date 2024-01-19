package org.alexgolikov.filter.contract;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceValueResult;

import java.util.List;

public interface TypeFilterable {
    ServiceValueResult<TypeData> filterToTypes(List<String> data);
}
