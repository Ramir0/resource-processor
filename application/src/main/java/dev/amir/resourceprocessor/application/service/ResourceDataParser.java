package dev.amir.resourceprocessor.application.service;

import dev.amir.resourceprocessor.domain.entity.ResourceData;
import dev.amir.resourceprocessor.domain.entity.Song;

public interface ResourceDataParser {
    Song parse(ResourceData resourceData);
}
