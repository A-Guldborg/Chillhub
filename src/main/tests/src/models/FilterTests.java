package models;

import com.exam.chillhub.models.*;

import com.exam.chillhub.models.Series;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTests {
    private Filter filter;

    @BeforeEach
    public void setUp() {
        // Start filter med 5 forskellige medier, både film og serier
        filter = new Filter("TestFilter");
        for (int i = 1; i <= 5; i++) {
            Media m;
            if (i % 2 == 1) {
                m = new Movie("Test" + i);
            } else {
                m = new Series("Test" + i);
            }
            filter.addToFilter(m);
        }
    }

    @Test
    public void testAddToFilter() {
        Movie mov = new Movie("Test123");
        filter.addToFilter(mov);
        assertTrue(filter.getFilteredData().contains(mov));
    }

    @Test
    public void testFilteredByType_Movie() {
        List<Media> filteredList = filter.getFilteredType(MediaType.MOVIE);
        for (Media m : filteredList) {
            assertEquals(m.getType(), MediaType.MOVIE);
        }
    }

    @Test
    public void testFilteredByType_Series() {
        List<Media> filteredList = filter.getFilteredType(MediaType.SERIES);
        for (Media m : filteredList) {
            assertEquals(m.getType(), MediaType.SERIES);
        }
    }
}
