package com.exam.chillhub.models;

import com.exam.chillhub.ChillhubApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.io.IOException;

public abstract class Media {
    private final StringProperty name;
    private final MediaType type;
    private final DoubleProperty rating;
    private final StringProperty year;

    public Media(MediaType type, String name, String year, double rating) {
        this.name = new SimpleStringProperty(name);
        this.type = type;
        this.rating = new SimpleDoubleProperty(rating);
        this.year = new SimpleStringProperty(year);
    }

    public String getName() {
        return this.name.get();
    }
    public MediaType getType() {
        return this.type;
    }
    public double getRating() {
        return this.rating.get();
    }
    public String getYear() {
        return this.year.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty ratingProperty() {
        return rating;
    }
    public StringProperty yearProperty() {
        return year;
    }

    /**
     * Returnér det `Image` som tilhører mediet eller `null` hvis mediet ikke har et tilknyttet billede.
     * @return `Image` tilhørende mediet.
     * @throws IOException hvis billedet ikke kunne åbnes.
     */
    public Image getPoster() throws IOException {
        var filename = getType().name().toLowerCase() + "/posters/" + getName() + ".jpg";
        var res = ChillhubApplication.class.getResource(filename);
        if (res == null) {
            return null;
        }
        return new Image(res.openStream());
    }
}
