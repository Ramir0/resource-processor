package dev.amir.resourceprocessor.framework.input.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.ContainerCustomizer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RabbitMqConfiguration {
    @Value("${spring.rabbitmq.queues.resource-process}")
    private String resourceProcessQueue;

    @Bean
    ContainerCustomizer<SimpleMessageListenerContainer> containerCustomizer() {
        return (container) -> container.setObservationEnabled(true);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue createResourceProcessQueue() {
        return new Queue(resourceProcessQueue);
    }
}
