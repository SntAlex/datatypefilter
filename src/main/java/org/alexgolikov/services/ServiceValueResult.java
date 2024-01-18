package org.alexgolikov.services;

public final class ServiceValueResult<T> extends ServiceResult {
    private final T value;

    public ServiceValueResult(String errorMessage) {
        super(errorMessage);
        this.value = null;
    }

    public ServiceValueResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}