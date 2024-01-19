package org.alexgolikov.configuration.contract;

import org.alexgolikov.resultdata.ServiceValueResult;
import org.apache.commons.cli.Options;

public interface ConfigurationParsable {
    ServiceValueResult<Options> retrieveParserOptions(String configurationFilename);
}
