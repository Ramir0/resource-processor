package dev.amir.resourceprocessor.application.retry;

@FunctionalInterface
public interface RetryFunction<T> {
    T execute();
}
