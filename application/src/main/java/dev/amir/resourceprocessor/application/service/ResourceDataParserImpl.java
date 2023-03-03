package dev.amir.resourceprocessor.application.service;

import dev.amir.resourceprocessor.domain.entity.ResourceData;
import dev.amir.resourceprocessor.domain.entity.Song;
import dev.amir.resourceprocessor.domain.exception.InvalidResourceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Service
public class ResourceDataParserImpl implements ResourceDataParser {

    private static final String LABEL_NAME = "dc:title";
    private static final String LABEL_ARTIST = "xmpDM:artist";
    private static final String LABEL_ALBUM = "xmpDM:album";
    private static final String LABEL_YEAR = "xmpDM:releaseDate";
    private static final String LABEL_LENGTH = "xmpDM:duration";

    private final Parser parser;
    private final ContentHandler handler;
    private final Metadata metadata;
    private final ParseContext parseContext;

    public ResourceDataParserImpl() {
        handler = new BodyContentHandler();
        metadata = new Metadata();
        parseContext = new ParseContext();
        parser = new Mp3Parser();
    }

    @Override
    public Song parse(ResourceData resourceData) {
        try {
            log.info("Parsing ResourceData with Id: {}", resourceData.getId());
            var metadata = getMetadata(resourceData);
            var song = buildSong(resourceData.getId(), metadata);
            log.info("Parsed {}", song);
            return song;
        } catch (Exception exception) {
            throw new InvalidResourceException(resourceData.getId(), exception);
        }
    }

    private Song buildSong(Long resourceId, Metadata metadata) {
        var yearString = metadata.get(LABEL_YEAR);
        int year = Integer.parseInt(yearString.substring(0, 4));
        return Song.builder()
                .resourceId(resourceId)
                .name(metadata.get(LABEL_NAME))
                .artist(metadata.get(LABEL_ARTIST))
                .album(metadata.get(LABEL_ALBUM))
                .year(year)
                .length(metadata.get(LABEL_LENGTH))
                .build();
    }

    private Metadata getMetadata(ResourceData resourceData) throws TikaException, IOException, SAXException {
        var input = new ByteArrayInputStream(resourceData.getData());
        parser.parse(input, handler, metadata, parseContext);
        return metadata;
    }
}
