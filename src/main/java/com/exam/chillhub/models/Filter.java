package com.exam.chillhub.models;
import java.util.*;

public class Filter {
    private final String title;
    private List<Media> filteredData;
    public Filter(String title) {
        this.title = title;
        filteredData = new ArrayList<>();
    }

    public void addToFilter(Media media) {
        filteredData.add(media);
    }

    public String getTitle() {
        return this.title;
    }

    public List<Media> getFilteredData() {
        return this.filteredData;
    }
}
