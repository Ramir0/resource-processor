package dev.amir.resourceprocessor.application.port.output;

import dev.amir.resourceprocessor.domain.entity.Song;

import java.util.Collection;
import java.util.Optional;

public interface SongServiceOutputPort {
    Long saveSong(Song song);

    Optional<Song> getSongById(Long songId);

    Collection<Long> deleteSongById(Collection<Long> ids);
}
