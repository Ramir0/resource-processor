package dev.amir.resourceprocessor.framework.output.rest;

import dev.amir.resourceprocessor.application.port.output.SongServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.Song;
import dev.amir.resourceprocessor.framework.output.rest.client.SongServiceRestClient;
import dev.amir.resourceprocessor.framework.output.rest.mapper.CreateSongRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SongServiceRestAdapter implements SongServiceOutputPort {

    private final SongServiceRestClient songServiceRestClient;
    private final CreateSongRequestMapper createSongRequestMapper;

    @Override
    public Long saveSong(Song song) {
        var request = createSongRequestMapper.convert(song);
        log.info("Saving [{}]", request);
        var response = songServiceRestClient.saveSong(request);
        return response.id();
    }
}
