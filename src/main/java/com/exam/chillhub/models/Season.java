package com.exam.chillhub.models;

public class Season {
    private final int number;
    private final int episodes;

    public Season(int number, int episodes) {
        if (episodes < 0)
            throw new IllegalArgumentException("episodes");
        this.number = number;
        this.episodes = episodes;
    }

    public int getSeasonNumber() {
        return this.number;
    }

    public int getEpisodes() {
        return this.episodes;
    }
}
