package org.alexgolikov.statistic.contract;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.resultdata.ServiceValueResult;
import org.alexgolikov.statistic.model.StatisticShort;

public interface Statistical {
    ServiceValueResult<StatisticShort> analyse(Boolean isFullData, TypeData typeData);
}
