package dev.amir.resourceprocessor.application.retry;

@FunctionalInterface
public interface RetryCallback<T> {
    T execute();
}
