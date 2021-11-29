package com.exam.chillhub.models;

public abstract class Media {
    private final String name;
    private final int type;
    // 1 = film, 2 = serie, kan evt. laves til eksplicit string eller lignende
    private String poster; // image eller filnavn
    public Media(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
        // 1 = film, 2 = serie
    }
}
