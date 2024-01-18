package org.alexgolikov.services.cli.options;

import org.alexgolikov.services.cli.exceptions.DuplicateFileException;
import org.alexgolikov.services.cli.exceptions.DuplicateOptionException;

import java.util.*;

public class Options {
    private final Set<String> inputFiles;
    private final HashMap<String, String> additionalOptions;

    public Options() {
        this.inputFiles = new HashSet<>();
        this.additionalOptions = new HashMap<>();
    }

    public void addFile(String file) throws DuplicateFileException {
        if (inputFiles.contains(file)) {
            throw new DuplicateFileException(file);
        }

        inputFiles.add(file);
    }

    public void addAdditionalOption(String key) throws DuplicateOptionException {
        if (additionalOptions.containsKey(key)) {
            throw new DuplicateOptionException(key);
        }

        additionalOptions.put(key, "");
    }

    public void insertAdditionalData(String key, String additionalValue) throws NoSuchElementException {
        checkOptionKey(key);
        additionalOptions.put(key, additionalValue);
    }

    public Set<String> getInputFiles() {
        return Collections.unmodifiableSet(inputFiles);
    }

    public String getAdditionalOptions(String key) throws NoSuchElementException {
        checkOptionKey(key);
        return additionalOptions.get(key);
    }

    private void checkOptionKey(String key) throws NoSuchElementException {
        if (!additionalOptions.containsKey(key)) {
            throw new NoSuchElementException("Option is not exist");
        }
    }
}