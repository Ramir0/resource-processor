package dev.amir.resourceprocessor.framework.output.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSongResponse {
    private String name;
    private String artist;
    private String album;
    private String length;
    private Long resourceId;
    private Integer year;
}
