package dev.amir.resourceprocessor.configuration;

import dev.amir.resourceprocessor.application.usecase.SongManagementUseCase;
import dev.amir.resourceprocessor.domain.entity.Song;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppInitializer {
    private final SongManagementUseCase songManagementUseCase;

    @PostConstruct
    public void init() {
        var songId = createSongCase();
        getSongCase(songId);
        deleteSongCase(songId);
    }

    private Long createSongCase() {
        log.info("CREATE SONG");
        var song = Song.builder()
                .resourceId(123L)
                .name("Song Name 1")
                .artist("Artist 1")
                .album("Album 1")
                .length("5:34")
                .year(2001)
                .build();
        log.info(song.toString());
        var songId = songManagementUseCase.createSong(song);
        log.info("Saved Song Id: {}", songId);
        return songId;
    }

    private void getSongCase(Long songId) {
        log.info("GET SONG");
        log.info("Song Id: {}", songId);
        var song = songManagementUseCase.getSongById(songId);
        log.info(song.toString());
    }

    private void deleteSongCase(Long songId) {
        log.info("DELETE SONG");
        var songIds = List.of(songId);
        log.info("Song Ids: {}", songIds);
        var deletedSongIds = songManagementUseCase.deleteSongById(songIds);
        log.info("Deleted Song Ids: {}", deletedSongIds);
    }
}
