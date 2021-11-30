package models;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.MediaType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class MediaTests {
    class ConcreteMedia extends Media {
        public ConcreteMedia(String name, MediaType type) {
            super(name, type);
        }
    }

    @Test
    void getPoster() {
        var media = new ConcreteMedia("ET", MediaType.MOVIE);
        try {
            var image = media.getPoster();
            if (image == null) {
                fail("Image is null");
            }
            fail(image.getUrl());
        } catch (IOException e) {
            fail("IOException when getting poster");
        }
    }
}
