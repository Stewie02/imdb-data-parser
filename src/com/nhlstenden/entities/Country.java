package com.nhlstenden.entities;

import java.util.ArrayList;
import java.util.List;
import static com.nhlstenden.FormatMethods.toCsvField;


/**
 * This Country class represents the countries that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Country implements Entity, HasId {

    private final int id;
    private final String country;
    private final List<Movie> movies;

    /**
     * This creates the new Country object
     * In the parameters it takes the new id and country name.
     * @param id id of the new object
     * @param country country name of the country
     */
    public Country(int id, String country) {
        this.movies = new ArrayList<>();

        this.id = id;
        this.country = country;
    }

    @Override
    public String toCSV() {
        return toCsvField(id) + ','
                + toCsvField(country);
    }

    public void addMovie(Movie movie) {
        // Let's check if the movie isn't already added 1 call ago!
        if (movies.get(movies.size() -1) != movie)
            movies.add(movie);
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getHeader() {
        return "id,country";
    }

}
