package org.alexgolikov.configuration.model;

public class MappedOptions {
    private String path;
    private String prefix;
    private String adding;
    private String shortStatistics;
    private String fullStatistics;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAdding() {
        return adding;
    }

    public void setAdding(String adding) {
        this.adding = adding;
    }

    public String getShortStatistics() {
        return shortStatistics;
    }

    public void setShortStatistics(String shortStatistics) {
        this.shortStatistics = shortStatistics;
    }

    public String getFullStatistics() {
        return fullStatistics;
    }

    public void setFullStatistics(String fullStatistics) {
        this.fullStatistics = fullStatistics;
    }
}
