package com.nhlstenden.entities;

import java.util.HashMap;
import java.util.Map;

public class Movies {

    private final Map<String, Movie> movieMap;

    public Movies() {
        movieMap = new HashMap<>();
    }

    public Movie findMovie(String title, String year, String movieNamePerYear) {
        return movieMap.get(title + year + movieNamePerYear);
    }

    public Movie findMovie(String title, int year, String movieNamePerYear) {
        return this.findMovie(title, Integer.toString(year), movieNamePerYear);
    }

    public void addMovie(Movie movie) {
        if (movieMap.containsKey(movie.getTitle() + movie.getYear() + movie.getMovieNamePerYear()))
            System.out.println("Key is the same!! " + movie.getTitle());
        movieMap.put(movie.getTitle() + movie.getYear() + movie.getMovieNamePerYear(), movie);
    }

    public Map<String, Movie> getMap() {
        return movieMap;
    }

}
