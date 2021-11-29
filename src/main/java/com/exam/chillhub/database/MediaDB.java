package com.exam.chillhub.database;
import com.exam.chillhub.models.*;

import java.io.*;
import java.util.*;

public class MediaDB {
    private static List<Media> MediaDB;
    private static Map<String, Filter> Categories;
    private final static String moviesPath = "com/exam/chillhub/movie/db.txt";
    private final static String seriesPath = "com/exam/chillhub/series/db.txt";

    private MediaDB() {}
    public static List<Media> getDB() {
        if (MediaDB == null) {
            MediaDB = new ArrayList<>();
            Categories = new HashMap<>();
            addMovies();
            addSeries();
        }
        return MediaDB;
    }

    public static Map<String, Filter> getCategories() {
        if (Categories == null) {
            getDB();
        }
        return Categories;
    }

    private static void addMovies() {
        try {
            Scanner inputFile = new Scanner(new File(moviesPath));
            while (inputFile.hasNext()) {
                String[] input = inputFile.nextLine().split(";");
                Movie movie = new Movie(input[0], input[1].trim(), Double.parseDouble(input[3]));
                MediaDB.add(movie);
                addToCategories(movie, input[2].split(","));
            }
        } catch (IOException e) {
            // TODO sikr håndtering af fejl ved DB-load
        }
    }

    private static void addSeries() {
        try {
            Scanner inputFile = new Scanner(new File(seriesPath));
            String[] input = inputFile.nextLine().split(";");
            Series series = new Series(input[0], input[1].trim(), Double.parseDouble(input[3]));
            addToCategories(series, input[2].split(","));
            for (int i = 4; i < input.length; i++) {
                String[] seasons = input[i].split(",");
                for (String season : seasons) {
                    String[] seasonAndEpisodes = season.trim().split("-");
                    series.addSeason(Integer.parseInt(seasonAndEpisodes[0]), Integer.parseInt(seasonAndEpisodes[1]));
                }
            }
        } catch (IOException e) {
            // TODO sikr håndtering af fejl ved DB-load
        }
    }

    private static void addToCategories(Media media, String[] categories) {
        for (String category : categories) {
            String name = category.trim();
            Categories.putIfAbsent(name, new Filter(name));
            Categories.get(name).addToFilter(media);
        }
    }
}
