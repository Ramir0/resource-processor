package dev.amir.resourceprocessor.framework.output.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongRequest {
    private Long resourceId;
    private String name;
    private String artist;
    private String album;
    private Integer year;
    private String length;
}

