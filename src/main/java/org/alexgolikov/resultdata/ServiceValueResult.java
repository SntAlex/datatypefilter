package org.alexgolikov.resultdata;

public final class ServiceValueResult<T> extends ServiceResult {
    private final T value;

    public ServiceValueResult(String errorMessage) {
        super(errorMessage);
        this.value = null;
    }

    public ServiceValueResult(Exception exception, String errorMessage) {
        super(exception, errorMessage);
        this.value = null;
    }

    public ServiceValueResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}