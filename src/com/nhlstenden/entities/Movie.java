package com.nhlstenden.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This Movie class represents the movies that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Movie implements Entity {

    private int id;
    private String title;
    private int year;
    private double rating;
    private int runningTime;
    private int budget;
    private int revenue;

    private final List<Actor> actors;
    private final List<Genre> genres;
    private final List<Country> countries;

    /**
     * Creates the new Movie object
     * @param id the id of the new object
     * @param title the title of the new object
     * @param year the year of the new object
     */
    public Movie(int id, String title, int year) {
        actors = new ArrayList<>();
        genres = new ArrayList<>();
        countries = new ArrayList<>();

        this.id = id;
        this.title = title;
        this.year = year;
    }

    /**
     * Return the CSV record
     * @return the CSV record
     */
    @Override
    public String toCSV() {
        return id + ',' +
                title + ',' +
                year + ',' +
                rating + ',' +
                runningTime + ',' +
                budget + ',' +
                revenue;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public List<Country> getCountries() {
        return this.countries;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    /**
     * Return the header of the CSV file
     * @return the CSV file header
     */
    public static String getHeader() {
        return "id,title,year,rating,runningTime,budget,revenue";
    }
}
