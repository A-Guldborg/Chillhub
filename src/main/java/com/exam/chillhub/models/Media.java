package com.exam.chillhub.models;

import static com.exam.chillhub.ChillhubApplication.openResource;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.io.IOException;

public abstract class Media {
    private final StringProperty name;
    private final MediaType type;
    private final DoubleProperty rating;
    private final StringProperty year;
    private final BooleanProperty favorite;
    private final int idx;

    public Media(MediaType type, String name, String year, double rating, boolean favorite, int idx) {
        this.name = new SimpleStringProperty(name);
        this.type = type;
        this.rating = new SimpleDoubleProperty(rating);
        this.year = new SimpleStringProperty(year);
        this.favorite = new SimpleBooleanProperty(favorite);
        this.idx = idx;
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
    public boolean getFavorite() {
        return this.favorite.get();
    }
    public int getIdx() {
        return this.idx;
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
    public BooleanProperty favoriteProperty() {
        return favorite;
    }

    /**
     * Returnér det `Image` som tilhører mediet eller `null` hvis mediet ikke har et tilknyttet billede.
     * @return `Image` tilhørende mediet.
     * @throws RuntimeException hvis billedet ikke kunne åbnes.
     */
    public Image getPoster() {
        var filename = getType().name().toLowerCase() + "/posters/" + getName() + ".jpg";
        var res = openResource(filename);

        if (res == null) {
            return null;
        }

        return new Image(res);
    }
}
