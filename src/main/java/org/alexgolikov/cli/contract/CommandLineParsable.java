package org.alexgolikov.cli.contract;

import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.apache.commons.cli.Options;

public interface CommandLineParsable {
    ServiceValueResult<ParsedData> parse(Options options, String[] args);
}
