package com.exam.chillhub.models;

public class Movie extends Media {
    public Movie(String name, String year, double rating) {
        super(MediaType.MOVIE, name, year, rating);
    }
}
