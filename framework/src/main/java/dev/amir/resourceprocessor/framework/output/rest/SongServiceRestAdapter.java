package dev.amir.resourceprocessor.framework.output.rest;

import dev.amir.resourceprocessor.framework.output.rest.client.SongServiceRestClient;
import dev.amir.resourceprocessor.framework.output.rest.mapper.CreateSongRequestMapper;
import dev.amir.resourceprocessor.framework.output.rest.mapper.GetSongResponseMapper;
import dev.amir.resourceprocessor.application.port.output.SongServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongServiceRestAdapter implements SongServiceOutputPort {

    private final SongServiceRestClient songServiceRestClient;
    private final CreateSongRequestMapper createSongRequestMapper;
    private final GetSongResponseMapper getSongResponseMapper;

    @Override
    public Long saveSong(Song song) {
        var request = createSongRequestMapper.convert(song);
        var response = songServiceRestClient.saveSong(request);

        return response.getId();
    }

    @Override
    public Optional<Song> getSongById(Long songId) {
        var response = songServiceRestClient.getSongById(songId);
        return response.map(getSongResponseMapper::convert);
    }

    @Override
    public Collection<Long> deleteSongById(Collection<Long> ids) {
        var idsString = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
        var response = songServiceRestClient.deleteSongById(idsString);
        return response.getIds();
    }
}
