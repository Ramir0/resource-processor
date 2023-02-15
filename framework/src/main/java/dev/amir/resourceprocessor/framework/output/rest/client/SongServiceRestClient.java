package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.framework.output.rest.request.CreateSongRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.CreateSongResponse;

public interface SongServiceRestClient {
    CreateSongResponse saveSong(CreateSongRequest request);
}
