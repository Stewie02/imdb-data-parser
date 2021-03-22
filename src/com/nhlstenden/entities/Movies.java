package com.nhlstenden.entities;

import java.util.HashMap;
import java.util.Map;

public class Movies {

    private final Map<String, Movie> movieMap;

    public Movies() {
        movieMap = new HashMap<>();
    }

    public Movie findMovie(String title, String year) {
        return movieMap.get(title + year);
    }

    public Movie findMovie(String title, int year) {
        return this.findMovie(title, Integer.toString(year));
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getTitle() + movie.getYear(), movie);
    }

    public Map<String, Movie> getMap() {
        return movieMap;
    }

}
