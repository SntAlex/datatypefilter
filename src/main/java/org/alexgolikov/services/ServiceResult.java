package org.alexgolikov.services;

public class ServiceResult {
    protected final Boolean successful;
    protected final String errorMessage;

    public ServiceResult() {
        this.successful = true;
        this.errorMessage = "";
    }

    public ServiceResult(String errorMessage) {
        this.successful = false;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
