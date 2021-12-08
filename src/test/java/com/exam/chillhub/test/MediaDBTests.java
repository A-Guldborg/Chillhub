package com.exam.chillhub.test;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.models.CategoryType;
import com.exam.chillhub.models.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MediaDBTests {
    private Filter media;
    private Map<CategoryType, Filter> categories;

    @BeforeEach
    public void setUp() {
        media = MediaDB.instance.getDB();
        categories = MediaDB.instance.getCategories();
    }

    @Test
    public void testAllCategoriesExists() {
        CategoryType[] expected = {CategoryType.CRIME, CategoryType.DRAMA, CategoryType.BIOGRAPHY, CategoryType.SPORT, CategoryType.HISTORY, CategoryType.ROMANCE, CategoryType.WAR, CategoryType.MYSTERY, CategoryType.ADVENTURE, CategoryType.FAMILY, CategoryType.FANTASY, CategoryType.THRILLER, CategoryType.HORROR, CategoryType.FILMNOIR, CategoryType.ACTION, CategoryType.SCIFI, CategoryType.COMEDY, CategoryType.MUSICAL, CategoryType.WESTERN, CategoryType.MUSIC, CategoryType.TALKSHOW, CategoryType.DOCUMENTARY, CategoryType.ANIMATION};
        for (CategoryType category : expected) {
            assertTrue(categories.containsKey(category), "missing: " + category);
            assertTrue(categories.get(category).getFilteredData().size() > 0, category + " is empty");
        }
    }

    @Test
    public void testAllMediaExists() {
        assertEquals(200, media.getFilteredData().size(), "Should read 200 media but reads: " + media.getFilteredData().size() + " instead");
    }
}
