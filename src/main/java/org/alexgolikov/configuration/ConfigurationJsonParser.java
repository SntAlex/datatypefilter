package org.alexgolikov.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alexgolikov.configuration.contract.ConfigurationParsable;
import org.alexgolikov.configuration.model.OptionsConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfigurationJsonParser implements ConfigurationParsable {
    @Override
    public ServiceValueResult<OptionsConfiguration> retrieveParserOptions(String configurationFilename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String resourceFile = getResourceFileAsString(configurationFilename);

            OptionsConfiguration optionsConfiguration = objectMapper.readValue(resourceFile, OptionsConfiguration.class);

            return new ServiceValueResult<>(optionsConfiguration);
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
