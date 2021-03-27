package com.nhlstenden.entities;

import java.util.ArrayList;
import java.util.List;
import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This Genre class represents the genres that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Genre implements Entity, HasId {

    private final int id;
    private final String genre;
    private final List<Movie> movies;

    /**
     * Creates the Genre object with the given parameters
     * @param id The id of the new Genre object
     * @param genre The name of the new Genre object
     */
    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
        movies = new ArrayList<>();
    }

    /**
     * Returns the CSV record of a genre object
     * @return the CSV record of the object
     */
    @Override
    public String toCSV() {
        return toCsvField(id) + ',' +
                toCsvField(genre);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns the headers of the CSV file
     * @return String with the headers of the CSV
     */
    public static String getHeader() {
        return "id,genre";
    }
}
