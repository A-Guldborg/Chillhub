package models;

import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Movie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilterTests {
    @Test
    public void testAddToFilter() {
        Movie mov = new Movie("Test123");
        Filter filter = new Filter("TestFilter");
        filter.addToFilter(mov);
        assertTrue(filter.getFilteredData().contains(mov));
    }
}
