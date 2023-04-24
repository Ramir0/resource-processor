package dev.amir.resourceprocessor.application.port.output;

import dev.amir.resourceprocessor.domain.entity.ResourceData;

public interface ResourceDataServiceOutputPort {
    ResourceData getResourceDataById(Long resourceId);
    String completeProcess(Long resourceId);
}
