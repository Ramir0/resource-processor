package dev.amir.resourceprocessor.application.retry.service;

import dev.amir.resourceprocessor.application.retry.RetryCallback;

public interface RetryExecutorService {
    <T> T execute(RetryCallback<T> callback);
}
