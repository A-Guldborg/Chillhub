package com.exam.chillhub.models;
import java.util.*;
import javafx.scene.paint.Color;

public class User {
    private String name;
    private Color color;
    private List<Media> favorites;

    public User(String name) {
        this.name = name;
        this.favorites = new ArrayList<>();
    }

    public User(String name, Color color, List<Media> favorites) {
        this.name = name;
        this.color = color;
        this.favorites = favorites;
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
        return color;
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
