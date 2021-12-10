package com.exam.chillhub.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.Random;

public class User extends Model {
    private StringProperty name;
    private Color color;
    private Filter favorites;

    public User(String name) {
        this(name, getRandomColor().toString());
    }

    public User(String name, String color) {
        this.name = new SimpleStringProperty(name);
        this.color = Color.valueOf(color);
        this.favorites = new Filter("Favorites");
    }

    private static Color getRandomColor() {
        Random random = new Random();
        double red = random.nextDouble();
        double green = random.nextDouble();
        double blue = random.nextDouble();
        return new Color(red, green, blue, 1);
    }

    public void generateColor() {
        color = getRandomColor();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Color getColor() {
        return this.color;
    }

    public String getColorString() {
        return color.toString();
    }

    public Filter getFavorites() {
        return favorites;
    }

    public void changeFavorite(Media media) {
        // Tjekker om det er muligt at fjerne media fra favorites, og hvis det ikke kan lade sig gøre tilføjer den i stedet
        if (!favorites.getFilteredData().remove(media)) {
            favorites.addToFilter(media);
        }
    }
}
