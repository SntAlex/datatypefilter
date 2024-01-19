package org.alexgolikov.cli.model;

public class FileWriterConfiguration {
    private final String prefix;
    private final String path;
    private final Boolean adding;

    public FileWriterConfiguration(String prefix, String path, Boolean adding) {
        this.prefix = prefix;
        this.path = path;
        this.adding = adding;
    }

    public String getPrefix() {
        return prefix;
    }

    public Boolean isAdding() {
        return adding;
    }

    public String getPath() {
        return path;
    }
}