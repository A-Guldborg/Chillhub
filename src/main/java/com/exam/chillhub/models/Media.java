package com.exam.chillhub.models;

import com.exam.chillhub.ChillhubApplication;
import javafx.scene.image.Image;

import java.io.IOException;

public abstract class Media {
    private final String name;
    private final MediaType type;
    private final double rating;
    private final String year;

    public Media(MediaType type, String name, String year, double rating) {
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.year = year;
    }
    public String getName() {
        return this.name;
    }
    public MediaType getType() {
        return this.type;
    }
    public Image getPoster() throws IOException {
        var filename = "database/" + getType().name().toLowerCase() + "/" + getName() + ".jpg";
        return new Image(ChillhubApplication.class.getResource(filename).openStream());
    }
    public double getRating() {
        return this.rating;
    }
    public String getYear() {
        return this.year;
    }
}
