package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.framework.output.rest.request.CreateSongRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.CreateSongResponse;
import dev.amir.resourceprocessor.framework.output.rest.response.DeleteSongResponse;
import dev.amir.resourceprocessor.framework.output.rest.response.GetSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceRestClientImpl implements SongServiceRestClient {
    private static final String SONGS = "songs";
    private final RestTemplate restTemplate;

    @Value("${services.song-service.url}")
    private String songServiceUrl;

    @Override
    public CreateSongResponse saveSong(CreateSongRequest request) {
        var postSongUrl = String.format("%s/%s", songServiceUrl, SONGS);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(postSongUrl, requestEntity, CreateSongResponse.class);
    }

    @Override
    public Optional<GetSongResponse> getSongById(Long songId) {
        var getSongUrl = String.format("%s/%s/%d", songServiceUrl, SONGS, songId);
        return Optional.ofNullable(restTemplate.getForObject(getSongUrl, GetSongResponse.class));
    }

    @Override
    public DeleteSongResponse deleteSongById(String ids) {
        var deleteSongUrl = String.format("%s/%s?id={ids}", songServiceUrl, SONGS);
        var entity = new HttpEntity<>(new HttpHeaders());
        var responseEntity = restTemplate.exchange(deleteSongUrl, HttpMethod.DELETE, entity, DeleteSongResponse.class, ids);
        return responseEntity.getBody();
    }
}
