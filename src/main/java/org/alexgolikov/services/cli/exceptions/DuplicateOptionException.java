package org.alexgolikov.services.cli.exceptions;

public class DuplicateOptionException extends Exception {
    public DuplicateOptionException(String option) {
        super(String.format("Option \"%s\" was added twice", option));
    }
}