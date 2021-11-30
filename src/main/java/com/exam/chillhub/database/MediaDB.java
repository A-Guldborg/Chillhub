package com.exam.chillhub.database;
import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.models.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

public class MediaDB {
    private List<Media> MediaDB;
    private Map<String, Filter> Categories;
    private final static String moviesPath = "movie/db.txt";
    private final static String seriesPath = "series/db.txt";
    private Scanner inputFile;

    public final static MediaDB instance;

    static {
        instance = new MediaDB();
    }

    private MediaDB() {
        MediaDB = new ArrayList<>();
        Categories = new HashMap<>();
        addMovies();
        addSeries();
    }

    public List<Media> getDB() {
        return MediaDB;
    }

    public Map<String, Filter> getCategories() {
        return Categories;
    }

    private void addMovies() {
        try {
            inputFile = new Scanner(Paths.get(ChillhubApplication.class.getResource(moviesPath).toURI()).toFile());
            while (inputFile.hasNext()) {
                String[] input = inputFile.nextLine().split(";");
                Movie movie = new Movie(input[0], input[1].trim(), Double.parseDouble(input[3].replace(',','.')));
                MediaDB.add(movie);
                addToCategories(movie, input[2].split(","));
            }
            inputFile.close();
        } catch (Exception e) {
            // TODO sikr håndtering af fejl ved DB-load
        }
    }

    private void addSeries() {
        try {
            inputFile = new Scanner(ChillhubApplication.class.getResource(seriesPath).openStream());
            while (inputFile.hasNext()) {
                String[] input = inputFile.nextLine().split(";");
                Series series = new Series(input[0], input[1].trim(), Double.parseDouble(input[3].replace(',', '.')));
                addToCategories(series, input[2].split(","));
                for (int i = 4; i < input.length; i++) {
                    String[] seasons = input[i].split(",");
                    for (String season : seasons) {
                        String[] seasonAndEpisodes = season.trim().split("-");
                        if (seasonAndEpisodes.length >= 2) {
                            series.addSeason(Integer.parseInt(seasonAndEpisodes[0]), Integer.parseInt(seasonAndEpisodes[1]));
                        }
                    }
                }
                MediaDB.add(series);
            }
            inputFile.close();
        } catch (IOException e) {
            // TODO sikr håndtering af fejl ved DB-load
        }
    }

    private void addToCategories(Media media, String[] categories) {
        for (String category : categories) {
            String name = category.trim();
            Categories.putIfAbsent(name, new Filter(name));
            Categories.get(name).addToFilter(media);
        }
    }
}
