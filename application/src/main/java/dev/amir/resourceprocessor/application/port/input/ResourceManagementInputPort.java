package dev.amir.resourceprocessor.application.port.input;

import dev.amir.resourceprocessor.application.port.output.ResourceDataServiceOutputPort;
import dev.amir.resourceprocessor.application.port.output.SongServiceOutputPort;
import dev.amir.resourceprocessor.application.retry.service.RetryExecutorService;
import dev.amir.resourceprocessor.application.service.ResourceDataParser;
import dev.amir.resourceprocessor.application.usecase.ResourceManagementUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceManagementInputPort implements ResourceManagementUseCase {
    private final ResourceDataServiceOutputPort resourceDataServiceOutputPort;
    private final ResourceDataParser resourceDataParser;
    private final SongServiceOutputPort songServiceOutputPort;
    private final RetryExecutorService retryExecutorService;

    @Override
    public void processResource(Long resourceId) {
        var resourceData = retryExecutorService.execute(() -> resourceDataServiceOutputPort.getResourceDataById(resourceId));
        var song = resourceDataParser.parse(resourceData);
        var songId = retryExecutorService.execute(() -> songServiceOutputPort.saveSong(song));
        log.info("SongId: {} processed based ResourceId: {}", songId, resourceId);
    }
}
