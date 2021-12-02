package com.exam.chillhub.test;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.MediaType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTests {

    class ConcreteMedia extends Media {
        public ConcreteMedia(MediaType type, String name, String year, double rating, boolean favorite, int idx) {
            super(type, name, year, rating, favorite, idx);
        }
    }

    @Test
    public void ctor() {
        var media = new ConcreteMedia(MediaType.MOVIE, "ET", "2012", 1, false, 0);
        assertEquals(media.getType(), MediaType.MOVIE);
        assertEquals(media.getName(), "ET");
        assertEquals(media.getYear(), "2012");
        assertEquals(media.getRating(), 1);
        assertEquals(media.getFavorite(), false);
        assertEquals(media.getIdx(), 0);
    }

    @Test
    void getPoster() {
        var media = new ConcreteMedia(MediaType.MOVIE, "ET", "2012", 1, false, 0);
        var image = media.getPoster();
        if (image == null)
            fail("Image is null");
    }

    @Test
    void getPoster_fail() {
        var media = new ConcreteMedia(MediaType.MOVIE, "Doesn't exist", "2012", 1, false, 0);
        var image = media.getPoster();
        if (image != null)
            fail("Image is not null");
    }
}
