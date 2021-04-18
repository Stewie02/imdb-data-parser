package com.nhlstenden.entities;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.interfaces.HasId;

import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This Movie class represents the movies that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Movie implements Entity, HasId {

    private final int id;
    private final String title;
    private final int year;
    private final String movieNamePerYear;
    private int runningTime = -1;

    /**
     * Creates the new Movie object
     * @param id the id of the new object
     * @param title the title of the new object
     * @param year the year of the new object
     */
    public Movie(int id, String title, int year, String movieNamePerYear) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.movieNamePerYear = movieNamePerYear;
    }

    /**
     * Return the CSV record
     * @return the CSV record
     */
    @Override
    public String toCSV() {
        return toCsvField(id) + ',' +
                toCsvField(title) + ',' +
                toCsvField(year) + ',' +
                toCsvField(runningTime);
    }

    @Override
    public EntityKey getKey() {
        return Movie.getKey(title, year, movieNamePerYear);
    }

    public int getId() {
        return id;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    /**
     * Return the header of the CSV file
     * @return the CSV file header
     */
    public String getHeader() {
        return "id,title,year,running_time_in_minutes";
    }

    /**
     * Returns the EntityKey of the given values in the parameters
     * @param title The title of the new EntityKey
     * @param year The year of the new EntityKey
     * @param movieNamePerYear The movie name per year of the new EntityKey
     * @return The newly created EntityKey
     */
    public static EntityKey getKey(String title, String year, String movieNamePerYear) {
        return Movie.getKey(title, FormatMethods.stringToYear(year), movieNamePerYear);
    }

    /**
     * Returns the EntityKey of the given values in the parameters
     * @param title The title of the new EntityKey
     * @param year The year of the new EntityKey
     * @param movieNamePerYear The movie name per year of the new EntityKey
     * @return The newly created EntityKey
     */
    public static EntityKey getKey(String title, int year, String movieNamePerYear) {
        if (movieNamePerYear == null)
            movieNamePerYear = "";
        return new EntityKey(title + year + movieNamePerYear);
    }

}
