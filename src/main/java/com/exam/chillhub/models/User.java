package com.exam.chillhub.models;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String name;
    private Color color;
    private List<Media> favorites;

    public User(String name) {
        this.name = name;
        this.favorites = new ArrayList<>();
    }

    public User(String name, String color) {
        this.name = name;
        this.color = Color.valueOf(color);
        this.favorites = new ArrayList<>();
    }

    private Color generateColor() {
        Random random = new Random();
        double red = random.nextDouble();
        double green = random.nextDouble();
        double blue = random.nextDouble();
        return new Color(red, green, blue, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    public String getColorString() {
        return color.toString();
    }

    public List<Media> getFavorites() {
        return favorites;
    }

    public void changeFavorite(Media media) {
        // Tjekker om det er muligt at fjerne media fra favorites, og hvis det ikke kan lade sig gøre tilføjer den i stedet
        if (!favorites.remove(media)) {
            favorites.add(media);
        }
    }
}
