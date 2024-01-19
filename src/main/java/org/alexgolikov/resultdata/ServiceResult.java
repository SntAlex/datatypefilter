package org.alexgolikov.resultdata;

public class ServiceResult {
    protected final Boolean successful;
    protected final String errorMessage;
    protected final Exception exception;

    public ServiceResult() {
        this.successful = true;
        this.errorMessage = "";
        this.exception = null;
    }

    public ServiceResult(String errorMessage) {
        this.successful = false;
        this.errorMessage = errorMessage;
        this.exception = null;
    }

    public ServiceResult(Exception exception, String errorMessage) {
        this.successful = false;
        this.errorMessage = errorMessage;
        this.exception = exception;
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Exception getException() {
        return this.exception;
    }
}
