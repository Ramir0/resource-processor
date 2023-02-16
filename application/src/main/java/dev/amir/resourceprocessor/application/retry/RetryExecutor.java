package dev.amir.resourceprocessor.application.retry;

@FunctionalInterface
public interface RetryExecutor {
    <T> T execute(RetryFunction<T> callback);
}
