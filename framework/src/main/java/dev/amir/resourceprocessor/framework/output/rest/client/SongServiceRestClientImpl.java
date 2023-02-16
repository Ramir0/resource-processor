package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.domain.exception.UnexpectedSongException;
import dev.amir.resourceprocessor.framework.output.rest.request.CreateSongRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.CreateSongResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SongServiceRestClientImpl implements SongServiceRestClient {
    private static final String SONGS = "songs";
    private final RestTemplate restTemplate;

    @Value("${services.song-service.url}")
    private String songServiceUrl;

    @Override
    public CreateSongResponse saveSong(CreateSongRequest songRequest) {
        var postSongUrl = String.format("%s/%s", songServiceUrl, SONGS);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(songRequest, headers);
        var response = restTemplate.postForEntity(postSongUrl, request, CreateSongResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
            throw new UnexpectedSongException(String.format(
                    "An error occurred during Song creating. ErrorCode: [%s] Request: [%s]",
                    response.getStatusCode(),
                    songRequest
            ));
        }

        log.debug("Retrieved: [{}]", response.getBody());
        return response.getBody();
    }
}
