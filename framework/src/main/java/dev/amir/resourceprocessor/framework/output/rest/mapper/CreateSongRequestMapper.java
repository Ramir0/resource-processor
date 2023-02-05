package dev.amir.resourceprocessor.framework.output.rest.mapper;

import dev.amir.resourceprocessor.domain.entity.Song;
import dev.amir.resourceprocessor.framework.output.rest.request.CreateSongRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateSongRequestMapper {
    CreateSongRequest convert(Song song);
}
