package com.exam.chillhub.models;

import com.exam.chillhub.ChillhubApplication;
import javafx.scene.image.Image;

import java.io.IOException;

public abstract class Media {
    private final String name;
    private final MediaType type;

    public Media(String name, MediaType type) {
        this.name = name;
        this.type = type;
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
}
