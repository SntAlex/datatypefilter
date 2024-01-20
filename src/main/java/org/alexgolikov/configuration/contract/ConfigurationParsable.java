package org.alexgolikov.configuration.contract;

import org.alexgolikov.configuration.model.OptionsConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;

public interface ConfigurationParsable {
    ServiceValueResult<OptionsConfiguration> retrieveParserOptions(String configurationFilename);
}
