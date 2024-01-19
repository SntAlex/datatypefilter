package org.alexgolikov.configuration.model;

public class OptionConfiguration {
    private String option;
    private Boolean anyAdditionalData;
    private String description;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean getAnyAdditionalData() {
        return anyAdditionalData;
    }

    public void setAnyAdditionalData(Boolean anyAdditionalData) {
        this.anyAdditionalData = anyAdditionalData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
