package org.alexgolikov.services.cli.contract;

import org.alexgolikov.services.ServiceValueResult;
import org.alexgolikov.services.cli.options.Options;

import java.util.List;

public interface OptionParsable {
    ServiceValueResult<Options> parse(List<String> parsingData);
}
