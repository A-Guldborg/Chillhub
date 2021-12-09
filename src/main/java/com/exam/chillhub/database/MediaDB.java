package com.exam.chillhub.database;

import com.exam.chillhub.models.*;

import java.util.*;

import static com.exam.chillhub.ChillhubApplication.openResource;

public class MediaDB {
    public final static MediaDB instance;
    private final static String moviesPath = "movie/db.txt";
    private final static String seriesPath = "series/db.txt";

    static {
        instance = new MediaDB();
    }

    private Filter MediaDB;
    private Map<CategoryType, Filter> Categories;
    private Scanner inputFile;

    private MediaDB() {
        MediaDB = new Filter("All media");
        Categories = new HashMap<>();
        addMovies();
        addSeries();
    }

    public Filter getDB() {
        return MediaDB;
    }

    public Map<CategoryType, Filter> getCategories() {
        return Categories;
    }

    private void addMovies() {
        inputFile = new Scanner(openResource(moviesPath));
        while (inputFile.hasNext()) {
            String[] input = inputFile.nextLine().split(";");
            Movie movie = new Movie(input[0], input[1].trim(), Double.parseDouble(input[3].replace(',', '.')), false, MediaDB.getFilteredData().size());
            MediaDB.addToFilter(movie);
            addToCategories(movie, input[2].split(","));
        }
        inputFile.close();
    }

    private void addSeries() {
        inputFile = new Scanner(openResource(seriesPath));
        while (inputFile.hasNext()) {
            String[] input = inputFile.nextLine().split(";");
            Series series = new Series(input[0], input[1].trim(), Double.parseDouble(input[3].replace(',', '.')), false, MediaDB.getFilteredData().size());
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
            MediaDB.addToFilter(series);
        }
        inputFile.close();
    }

    private void addToCategories(Media media, String[] categories) {
        for (String category : categories) {
            var cat = CategoryType.valueOf(category.trim().replace("-", "").toUpperCase());
            media.addCategory(cat);
            Categories.putIfAbsent(cat, new Filter(cat.toString()));
            Categories.get(cat).addToFilter(media);
        }
    }

    /**
     * Basic search returning a filter of all elements that has the searchstring in it's name.
     * Filters can filter only specific media type, i.e. search for only movies with "king" in the name etc.
     * @param searchstring String to be used when searching
     * @return Filter of all media
     */
    public Filter search(String searchstring) {
        String[] searchwords = searchstring.split(" ");
        ArrayList<ArrayList<Media>> mediaoccurences = new ArrayList<ArrayList<Media>>();
        for (String s : searchwords) {
            mediaoccurences.add(new ArrayList<>());
        }
        for (Media m : MediaDB.getFilteredData()) {
            String name = m.getName().toLowerCase();
            int occ = 0;
            for (String searchword : searchwords) {
                if (name.contains(searchword.toLowerCase())) {
                    occ++;
                }
            }
            if (occ > 0) {
                mediaoccurences.get(occ-1).add(m);
            }
        }
        Filter filter = new Filter(searchstring);
        for (int i = searchwords.length - 1; i >= 0; i--) {
            for (Media m : mediaoccurences.get(i)) {
                filter.addToFilter(m);
            }
        }
        return filter;
    }
}
