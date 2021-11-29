package com.exam.chillhub.models;
import java.util.*;

public class Series extends Media {
    private List<Season> seasons;
    public Series(String name) {
        super(name, 2);
        // type 2 == serie
        this.seasons = new ArrayList<>();
    }

    public int getNumberOfSeasons() {
        return seasons.size();
    }

    public int getNumberOfEpisodes() {
        int totalEpisodes = 0;
        for (Season season : seasons) {
            totalEpisodes += season.getEpisodes();
        }
        return totalEpisodes;
    }

    public List<Season> getSeasons() {
        return this.seasons;
    }
}
