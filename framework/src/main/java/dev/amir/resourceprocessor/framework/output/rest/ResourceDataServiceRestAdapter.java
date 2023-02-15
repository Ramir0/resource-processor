package dev.amir.resourceprocessor.framework.output.rest;

import dev.amir.resourceprocessor.application.port.output.ResourceDataServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.ResourceData;
import dev.amir.resourceprocessor.framework.output.rest.client.ResourceDataServiceRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResourceDataServiceRestAdapter implements ResourceDataServiceOutputPort {
    private final ResourceDataServiceRestClient resourceDataServiceRestClient;

    @Override
    public ResourceData getResourceDataById(Long resourceId) {
        var response = resourceDataServiceRestClient.getResourceDataById(resourceId);
        return ResourceData.builder()
                .id(resourceId)
                .data(response)
                .build();
    }
}
