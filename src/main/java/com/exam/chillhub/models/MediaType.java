package com.exam.chillhub.models;

public enum MediaType {
    ANY("Any"),
    MOVIE("Movie"),
    SERIES("Series");

    private String name;

    MediaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
