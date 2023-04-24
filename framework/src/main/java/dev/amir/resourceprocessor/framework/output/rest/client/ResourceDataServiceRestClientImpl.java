package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.domain.exception.InvalidResourceException;
import dev.amir.resourceprocessor.framework.output.rest.request.CompleteProcessRequest;
import dev.amir.resourceprocessor.framework.output.rest.request.GetResourceDataRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.CompleteProcessResponse;
import dev.amir.resourceprocessor.framework.output.rest.response.GetResourceDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceDataServiceRestClientImpl implements ResourceDataServiceRestClient {
    private static final String RESOURCE = "resources";
    private static final String COMPLETE_RESOURCE = "complete";
    private final RestTemplate restTemplate;

    @Value("${services.resource-service.url}")
    private String resourceServiceUrl;

    @Override
    public GetResourceDataResponse getResourceDataById(GetResourceDataRequest request) {
        var getResourceUrl = String.format("%s/%s/%s", resourceServiceUrl, RESOURCE, request.id());
        var response = restTemplate.getForEntity(getResourceUrl, byte[].class);
        if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
            throw new InvalidResourceException(request.id());
        }

        log.debug("Retrieved ResourceData size: [{}] for ResourceId: [{}]", response.getBody().length, request.id());
        return new GetResourceDataResponse(response.getBody());
    }

    @Override
    public CompleteProcessResponse completeProcess(CompleteProcessRequest request) {
        var completeResourceUrl = String.format("%s/%s/%s/%s", resourceServiceUrl, RESOURCE, request.id(), COMPLETE_RESOURCE);
        var response = restTemplate.exchange(completeResourceUrl, HttpMethod.POST, null, CompleteProcessResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
            throw new InvalidResourceException(request.id());
        }

        log.debug("Completed Resource with Id: [{}]", request.id());
        return response.getBody();
    }
}
