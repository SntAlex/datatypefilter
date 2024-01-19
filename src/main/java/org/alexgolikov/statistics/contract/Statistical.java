package org.alexgolikov.statistics.contract;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.alexgolikov.statistics.model.StatisticsShort;

public interface Statistical {
    ServiceValueResult<StatisticsShort> analyse(Boolean isFullData, TypeData typeData);
}
