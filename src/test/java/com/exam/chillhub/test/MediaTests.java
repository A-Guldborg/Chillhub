package com.exam.chillhub.test;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.MediaType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class MediaTests {
    class ConcreteMedia extends Media {
        public ConcreteMedia(MediaType type, String name, String year, double rating, boolean favorite, int idx) {
            super(type, name, year, rating, favorite, idx);
        }
    }

    @Test
    void getPoster() {
        var media = new ConcreteMedia(MediaType.MOVIE, "ET", "2012", 1, false, 0);
        try {
            var image = media.getPoster();
            if (image == null) {
                fail("Image is null");
            }
            // Test is successful if the image is not null
        } catch (IOException e) {
            fail("IOException when getting poster");
        }
    }
}
