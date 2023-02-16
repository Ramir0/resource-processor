package dev.amir.resourceprocessor.application.retry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryExecutorImpl implements RetryExecutor {
    private final RetryTemplate retryTemplate;

    @Override
    public <T> T execute(RetryFunction<T> callback) {
        return retryTemplate.execute(retryContext -> {
            if (retryContext.getRetryCount() > 0) {
                log.warn("Retry count: [{}] Error message: [{}]", retryContext.getRetryCount(), retryContext.getLastThrowable().getMessage());
            }
            return callback.execute();
        });
    }
}
