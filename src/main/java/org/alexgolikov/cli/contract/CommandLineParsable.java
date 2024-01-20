package org.alexgolikov.cli.contract;

import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.configuration.model.OptionsConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;

public interface CommandLineParsable {
    ServiceValueResult<ParsedData> parse(OptionsConfiguration configuration, String[] args);
}
