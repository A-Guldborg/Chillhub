package com.exam.chillhub.models;

import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.tests.MediaTests;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Locale;

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
        return new Image(ChillhubApplication.class.getResource("database/" + getType().name().toLowerCase(Locale.ROOT) + "/" + getName() + ".jpg").openStream());
    }
}
