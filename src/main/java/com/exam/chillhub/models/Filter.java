package com.exam.chillhub.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Filter {
    private final StringProperty title;
    private List<Media> filteredData;
    private HashMap<MediaType, Filter> cachedLists;

    public Filter(String title) {
        this.title = new SimpleStringProperty(title);
        filteredData = new ArrayList<>();
        cachedLists = new HashMap<>();
    }

    /**
     * Tilføjer et medie til filteret og tilføjer også mediet til en evt. cached filtrering af dette medies MediaType.
     *
     * @param media Det medie, der skal tilføjes til filteret.
     */
    public void addToFilter(Media media) {
        filteredData.add(media);

        // Tjekker om en cached liste er lavet over denne MediaType og tilføjer i så fald mediet
        if (cachedLists.containsKey(media.getType())) {
            cachedLists.get(media.getType()).addToFilter(media);
        }
    }

    public String getTitle() {
        return this.title.get();
    }

    public StringProperty titleProperty() {
        return this.title;
    }

    public List<Media> getFilteredData() {
        return this.filteredData;
    }

    /**
     * Returnerer en liste over det nuværende filter hvor kun serier bliver vist.
     * Metoden gemmer listen så den hurtigere kan blive fundet frem næste gang den samme søgning laves i samme session.
     *
     * @param type MediaType enum som filtreringsgrundlaget
     * @return List<Media> Listen over den filtrerede søgning
     */
    public Filter getFilteredType(MediaType type) {
        // Tjekker om listen allerede er skabt
        if (cachedLists.containsKey(type)) {
            return cachedLists.get(type);
        } else {
            // Alternativt søger den igennem filteret og tilføjer de enkelte elementer til en List der caches i et HashMap
            Filter filterByType = new Filter(this.title + " " + type);
            for (Media media : filteredData) {
                if (media.getType() == type) {
                    filterByType.addToFilter(media);
                }
            }
            cachedLists.put(type, filterByType);
            return filterByType;
        }
    }
}
