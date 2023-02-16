package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.domain.exception.InvalidResourceException;
import dev.amir.resourceprocessor.framework.output.rest.request.GetResourceDataRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.GetResourceDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceDataServiceRestClientImpl implements ResourceDataServiceRestClient {
    private static final String RESOURCE = "resources";
    private final RestTemplate restTemplate;

    @Value("${services.resource-service.url}")
    private String resourceServiceUrl;

    @Override
    public GetResourceDataResponse getResourceDataById(GetResourceDataRequest resourceDataRequest) {
        var getResourceUrl = String.format("%s/%s/%s", resourceServiceUrl, RESOURCE, resourceDataRequest.id());
        var response = restTemplate.getForEntity(getResourceUrl, byte[].class);
        if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
            throw new InvalidResourceException(resourceDataRequest.id());
        }

        log.debug("Retrieved ResourceData size: [{}] for ResourceId: [{}]", response.getBody().length, resourceDataRequest.id());
        return new GetResourceDataResponse(response.getBody());
    }
}
