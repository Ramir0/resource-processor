package dev.amir.resourceprocessor.application.retry.service;

import dev.amir.resourceprocessor.application.retry.RetryCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryExecutorServiceImpl implements RetryExecutorService {
    private final RetryTemplate retryTemplate;

    @Override
    public <T> T execute(RetryCallback<T> callback) {
        return retryTemplate.execute(retryContext -> {
            if (retryContext.getRetryCount() > 0) {
                log.warn("Retry count: {}", retryContext.getRetryCount());
            }
            return callback.execute();
        });
    }
}
