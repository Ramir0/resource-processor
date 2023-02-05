package dev.amir.resourceprocessor.application.usecase;

import dev.amir.resourceprocessor.domain.entity.Song;

import java.util.Collection;

public interface SongManagementUseCase {
    Long createSong(Song song);

    Song getSongById(Long songId);

    Collection<Long> deleteSongById(Collection<Long> ids);
}
