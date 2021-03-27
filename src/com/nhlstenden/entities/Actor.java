package com.nhlstenden.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This Actor class represents the actors that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Actor implements Entity, HasId {

    private int id;
    private String lastName;
    private String firstName;
    private final List<Movie> movies;

    /**
     * Creates the Actor object
     * @param id the id of this new object
     * @param firstName the firstname of this new object
     * @param lastName the lastname of this new object
     */
    public Actor(int id, String firstName, String lastName) {
        this.movies = new ArrayList<>();

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the string how the CSV record looks like
     * @return the CSV record of this object
     */
    @Override
    public String toCSV() {
        return toCsvField(id) + ',' +
                toCsvField(lastName) + ',' +
                toCsvField(firstName);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the header of the CSV file
     * @return Header of the CSV file
     */
    public static String getHeader() {
        return "id,lastName,FirstName";
    }

}
