package dev.amir.resourceprocessor.application.port.output;

import dev.amir.resourceprocessor.domain.entity.Song;

public interface SongServiceOutputPort {
    Long saveSong(Song song);
}
