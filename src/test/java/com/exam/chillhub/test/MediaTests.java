package com.exam.chillhub.test;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.MediaType;
import javafx.scene.image.Image;
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
        var image = media.getPoster();
        if (image == null) {
            fail("Image is null");
        }
        // Test is successful if the image is not null
    }

    @Test
    void getPoster_fail() {
        var media = new ConcreteMedia(MediaType.MOVIE, "Doesn't exist", "2012", 1, false, 0);
        var image = media.getPoster();
        if (image != null) {
            fail("Image is not null");
        }
        // Test is successful if the image is null
    }
}
