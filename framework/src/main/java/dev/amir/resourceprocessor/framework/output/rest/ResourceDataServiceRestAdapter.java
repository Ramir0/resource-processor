package dev.amir.resourceprocessor.framework.output.rest;

import dev.amir.resourceprocessor.application.port.output.ResourceDataServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.ResourceData;
import dev.amir.resourceprocessor.framework.output.rest.client.ResourceDataServiceRestClient;
import dev.amir.resourceprocessor.framework.output.rest.request.GetResourceDataRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceDataServiceRestAdapter implements ResourceDataServiceOutputPort {
    private final ResourceDataServiceRestClient resourceDataServiceRestClient;

    @Override
    public ResourceData getResourceDataById(Long resourceId) {
        log.info("Requesting ResourceData with Id: {}", resourceId);
        var request = new GetResourceDataRequest(resourceId);
        var response = resourceDataServiceRestClient.getResourceDataById(request);
        return ResourceData.builder()
                .id(resourceId)
                .data(response.data())
                .build();
    }
}
