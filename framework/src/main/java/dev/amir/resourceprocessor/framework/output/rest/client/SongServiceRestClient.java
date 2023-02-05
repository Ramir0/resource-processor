package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.framework.output.rest.response.CreateSongResponse;
import dev.amir.resourceprocessor.framework.output.rest.response.GetSongResponse;
import dev.amir.resourceprocessor.framework.output.rest.request.CreateSongRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.DeleteSongResponse;

import java.util.Optional;

public interface SongServiceRestClient {
    CreateSongResponse saveSong(CreateSongRequest request);

    Optional<GetSongResponse> getSongById(Long songId);

    DeleteSongResponse deleteSongById(String ids);
}
