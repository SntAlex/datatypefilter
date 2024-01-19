package org.alexgolikov.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alexgolikov.configuration.contract.ConfigurationParsable;
import org.alexgolikov.configuration.model.OptionConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfigurationJsonParser implements ConfigurationParsable {
    @Override
    public ServiceValueResult<Options> retrieveParserOptions(String configurationFilename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String resourceFile = getResourceFileAsString(configurationFilename);

            OptionConfiguration[] optionConfigurations = objectMapper.readValue(resourceFile, OptionConfiguration[].class);

            Options options = new Options();

            for (OptionConfiguration optionConfiguration : optionConfigurations) {
                options.addOption(new Option(optionConfiguration.getOption(), optionConfiguration.getAnyAdditionalData(), optionConfiguration.getDescription()));
            }

            return new ServiceValueResult<>(options);
        } catch (IOException ex) {
            return new ServiceValueResult<>(ex, String.format("Configuration file \"%s\" cannot be read", configurationFilename));
        } catch (NullPointerException ex) {
            return new ServiceValueResult<>(ex, String.format("Configuration file \"%s\" cannot be found in resources", configurationFilename));
        }
    }

    private String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            try (InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(is)); BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
