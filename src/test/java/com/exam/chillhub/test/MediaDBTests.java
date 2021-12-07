package com.exam.chillhub.test;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MediaDBTests {
    private List<Media> media;
    private Map<String, Filter> categories;

    @BeforeEach
    public void setUp() {
        media = MediaDB.instance.getDB();
        categories = MediaDB.instance.getCategories();
    }

    @Test
    public void testAllCategoriesExists() {
        String[] expected = {"Crime", "Drama", "Biography", "Sport", "History", "Romance", "War", "Mystery", "Adventure", "Family", "Fantasy", "Thriller", "Horror", "Film-Noir", "Action", "Sci-fi", "Comedy", "Musical", "Western", "Music", "Talk-show", "Documentary", "Animation"};
        for (String category : expected) {
            assertTrue(categories.containsKey(category), "missing: " + category);
            assertTrue(categories.get(category).getFilteredData().size() > 0, category + " is empty");
        }
    }

    @Test
    public void testAllMediaExists() {
        assertEquals(200, media.size(), "Should read 200 media but reads: " + media.size() + " instead");
    }
}
