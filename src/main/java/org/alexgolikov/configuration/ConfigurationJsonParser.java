package org.alexgolikov.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alexgolikov.configuration.contract.ConfigurationParsable;
import org.alexgolikov.configuration.model.OptionConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.File;
import java.io.IOException;

public class ConfigurationJsonParser implements ConfigurationParsable {
    @Override
    public ServiceValueResult<Options> retrieveParserOptions(String configurationFilename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OptionConfiguration[] optionConfigurations = objectMapper.readValue(new File(configurationFilename), OptionConfiguration[].class);

            Options options = new Options();

            for (OptionConfiguration optionConfiguration : optionConfigurations) {
                options.addOption(new Option(optionConfiguration.getOption(), optionConfiguration.getAnyAdditionalData(), optionConfiguration.getDescription()));
            }

            return new ServiceValueResult<>(options);
        } catch (IOException ex) {
            return new ServiceValueResult<>(ex, String.format("Configuration file \"%s\" cannot be read", configurationFilename));
        }
    }
}
