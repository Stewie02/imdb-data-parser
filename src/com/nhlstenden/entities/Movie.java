package com.nhlstenden.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This Movie class represents the movies that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Movie implements Entity, HasId {

    private int id;
    private String title;
    private int year;
    private int runningTime = -1;
    private final String movieNamePerYear;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getMovieNamePerYear() {
        return movieNamePerYear;
    }

    /**
     * Return the header of the CSV file
     * @return the CSV file header
     */
    public String getHeader() {
        return "id,title,year,rating,votes,running_time_in_minutes,budget,revenue";
    }
}
