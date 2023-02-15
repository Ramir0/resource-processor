package dev.amir.resourceprocessor.framework.input.rabbitmq;

import dev.amir.resourceprocessor.framework.input.rabbitmq.message.ProcessResourceMessage;
import dev.amir.resourceprocessor.framework.input.rabbitmq.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceMessageListenerRabbitMqAdapter {
    private final ResourceService resourceService;

    @RabbitListener(queues = {"q.resource-process"})
    public void onResourceProcess(ProcessResourceMessage message) {
        log.info("Process Resource message received: {}", message);
        resourceService.processResource(message);
    }
}
