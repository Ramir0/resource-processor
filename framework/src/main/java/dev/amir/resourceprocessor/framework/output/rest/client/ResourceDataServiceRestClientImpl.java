package dev.amir.resourceprocessor.framework.output.rest.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ResourceDataServiceRestClientImpl implements ResourceDataServiceRestClient {
    private static final String RESOURCE = "resources";
    private final RestTemplate restTemplate;

    @Value("${services.resource-service.url}")
    private String resourceServiceUrl;

    @Override
    public byte[] getResourceDataById(Long resourceId) {
        var getResourceUrl = String.format("%s/%s/%d", resourceServiceUrl, RESOURCE, resourceId);
        ResponseEntity<byte[]> response = restTemplate.getForEntity(getResourceUrl, byte[].class);
        return response.getBody();
    }
}
