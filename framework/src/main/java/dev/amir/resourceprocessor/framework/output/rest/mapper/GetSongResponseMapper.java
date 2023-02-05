package dev.amir.resourceprocessor.framework.output.rest.mapper;

import dev.amir.resourceprocessor.framework.output.rest.response.GetSongResponse;
import dev.amir.resourceprocessor.domain.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GetSongResponseMapper {
    Song convert(GetSongResponse response);
}
