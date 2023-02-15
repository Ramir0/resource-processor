package dev.amir.resourceprocessor.application.service;

import dev.amir.resourceprocessor.domain.entity.ResourceData;
import dev.amir.resourceprocessor.domain.entity.Song;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Service
public class ResourceDataParserImpl implements ResourceDataParser {
    @Override
    public Song parse(ResourceData resourceData) {
        try {
            var metadata = getMetadata(resourceData);
            return Song.builder()
                    .resourceId(resourceData.getId())
                    .album(metadata.get("xmpDM:album"))
                    .artist(metadata.get("xmpDM:artist"))
                    .name(metadata.get("dc:title"))
                    .length(metadata.get("xmpDM:duration"))
                    .year(Integer.parseInt(metadata.get("xmpDM:releaseDate")))
                    .build();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }

        return null;
    }

    private Metadata getMetadata(ResourceData resourceData) throws TikaException, IOException, SAXException {
        var input = new ByteArrayInputStream(resourceData.getData());
        var handler = new BodyContentHandler();
        var metadata = new Metadata();
        var parseContext = new ParseContext();

        var parser = new Mp3Parser();
        parser.parse(input, handler, metadata, parseContext);

        return metadata;
    }
}
