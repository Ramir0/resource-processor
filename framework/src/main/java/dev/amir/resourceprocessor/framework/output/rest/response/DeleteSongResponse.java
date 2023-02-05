package dev.amir.resourceprocessor.framework.output.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSongResponse {
    private List<Long> ids;
}
