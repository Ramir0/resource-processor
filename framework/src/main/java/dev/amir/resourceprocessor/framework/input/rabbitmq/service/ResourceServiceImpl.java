package dev.amir.resourceprocessor.framework.input.rabbitmq.service;

import dev.amir.resourceprocessor.application.usecase.ResourceManagementUseCase;
import dev.amir.resourceprocessor.framework.input.rabbitmq.message.ProcessResourceMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceManagementUseCase resourceManagementUseCase;

    @Override
    public void processResource(ProcessResourceMessage message) {
        try {
            resourceManagementUseCase.processResource(message.id());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
