package org.alexgolikov.services.cli.exceptions;

public class DuplicateFileException extends Exception {
    public DuplicateFileException(String file) {
        super(String.format("File \"%s\" was added twice", file));
    }
}