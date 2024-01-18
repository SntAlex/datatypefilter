package org.alexgolikov.services.cli.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OptionConfigurationsBuilder {
    private final Map<String, Boolean> optionConfigurationsDictionary;

    public OptionConfigurationsBuilder() {
        this.optionConfigurationsDictionary = new HashMap<>();
    }

    public OptionConfigurationsBuilder addConfiguration(String key, Boolean isOptionWithAdditionalData) {

        if (!optionConfigurationsDictionary.containsKey(key)) {
            this.optionConfigurationsDictionary.put(key, isOptionWithAdditionalData);
        }

        return this;
    }

    public Map<String, Boolean> getResult() {
        return Collections.unmodifiableMap(this.optionConfigurationsDictionary);
    }
}