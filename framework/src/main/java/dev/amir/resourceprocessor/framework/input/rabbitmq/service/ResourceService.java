package dev.amir.resourceprocessor.framework.input.rabbitmq.service;

import dev.amir.resourceprocessor.framework.input.rabbitmq.message.ProcessResourceMessage;

public interface ResourceService {
    void processResource(ProcessResourceMessage message);
}
