package dev.amir.resourceprocessor.framework.output.rest;

import dev.amir.resourceprocessor.application.port.output.SongServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.Song;
import dev.amir.resourceprocessor.framework.output.rest.client.SongServiceRestClient;
import dev.amir.resourceprocessor.framework.output.rest.mapper.CreateSongRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceRestAdapter implements SongServiceOutputPort {

    private final SongServiceRestClient songServiceRestClient;
    private final CreateSongRequestMapper createSongRequestMapper;

    @Override
    public Long saveSong(Song song) {
        var request = createSongRequestMapper.convert(song);
        var response = songServiceRestClient.saveSong(request);
        return response.getId();
    }
}
