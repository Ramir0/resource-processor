package dev.amir.resourceprocessor.application.port.input;

import dev.amir.resourceprocessor.application.usecase.SongManagementUseCase;
import dev.amir.resourceprocessor.application.port.output.SongServiceOutputPort;
import dev.amir.resourceprocessor.domain.entity.Song;
import dev.amir.resourceprocessor.domain.exception.SongNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongManagementInputPort implements SongManagementUseCase {
    private final SongServiceOutputPort songServiceOutputPort;

    @Override
    public Long createSong(Song song) {
        return songServiceOutputPort.saveSong(song);
    }

    @Override
    public Song getSongById(Long songId) {
        return songServiceOutputPort.getSongById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));
    }

    @Override
    public Collection<Long> deleteSongById(Collection<Long> ids) {
        return songServiceOutputPort.deleteSongById(ids);
    }
}
