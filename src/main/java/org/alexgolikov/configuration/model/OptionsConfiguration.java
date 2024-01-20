package org.alexgolikov.configuration.model;

import java.util.List;

public class OptionsConfiguration {
    private MappedOptions mappedOptions;
    private List<OptionConfiguration> availableOptions;

    public List<OptionConfiguration> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(List<OptionConfiguration> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public MappedOptions getMappedOptions() {
        return mappedOptions;
    }

    public void setMappedOptions(MappedOptions mappedOptions) {
        this.mappedOptions = mappedOptions;
    }
}
