package com.nhlstenden.parsers;

import com.nhlstenden.CurrencyConverter;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the budget and revenue from the business.list.
 */
public class BusinessParser extends LineByLineParser implements Parser {

    private final Movies movies;
    private Business business;

    private final Pattern moviePattern;
    private final Pattern revenuePattern;
    private final Pattern budgetPattern;
    private CurrencyConverter currencyConverter;

    /**
     * This creates the object and takes as a parameter the movieMap
     * @param movies Movies object containing all the movies
     */
    public BusinessParser(Movies movies) {
        this.movies = movies;
        this.fileName = "business.list";

        this.business = new Business();
        try {
            this.currencyConverter = new CurrencyConverter();
        } catch (IOException e) {
            System.out.println("Can't parse the currencies. Not parsing the business list");
            this.currencyConverter = null;
        }

        this.moviePattern = Pattern.compile("MV: \\\"?(.*?)\\\"?\\s+\\((\\d{4}).*\\)\\s(?!\\{)");
        this.revenuePattern = Pattern.compile("GR:\\s([A-z]+)\\s([0-9,]+)");
        this.budgetPattern = Pattern.compile("BT:\\s([A-z]+)\\s([0-9,]+)");
    }

    @Override
    protected void parseLine(String line) {
        if (currencyConverter == null) return;
        String kindOfInfo = line.substring(0, 2);

        // Let's see what's on the line, now we know what to do
        switch (kindOfInfo) {
            case "--" -> {
                updateMovie(business);
                business = new Business();
            }
            case "MV" -> parseMovieTitle(line, business);
            case "BT" -> business.addBudget(parseMovieBudget(line));
            case "GR" -> business.addRevenue(parseMovieRevenue(line));
        }
    }

    /**
     * This function updates all the information in the movie object if it exists
     * @param business The business object when the updated information
     */
    private void updateMovie(Business business) {
        Movie movie = movies.findMovie(business.getTitle(), business.getYear());
        if (movie != null) {
            movie.setBudget(business.getBudget());
            movie.setRevenue(business.getRevenue());
        }
        else {
            System.out.println("Movie: " + business.getTitle() + " wasn't in the movie map!");
        }
    }

    /**
     * Get's the movie title out of the line
     * @param line The line containing the movie title
     */
    private void parseMovieTitle(String line, Business business) {
        Matcher matcher = moviePattern.matcher(line);
        if (matcher.find()) {
            String movieTitle = matcher.group(1);
            String movieYear = matcher.group(2);

            business.setTitle(movieTitle);
            business.setYear(Integer.parseInt(movieYear));
        }
    }

    /**
     * Get's the movie budget out of the line
     * @param line The line containing the budget
     * @return The budget
     */
    private int parseMovieBudget(String line) {
        Matcher matcher = budgetPattern.matcher(line);
        if (matcher.find())
            return Integer.parseInt(matcher.group(2));
        return -1;
    }

    /**
     * Get's the movie revenue out of the line
     * @param line The line containing the revenue
     * @return The revenue
     */
    private int parseMovieRevenue(String line) {
        Matcher matcher = revenuePattern.matcher(line);
        if (matcher.find())
            return Integer.parseInt(matcher.group(2));
        return -1;
    }

}

/**
 * This is a small entity class with the information from the business.list
 */
class Business {

    private String title;
    private int revenue;
    private int budget;
    private int year;

    /**
     * Adds the revenue to the object
     * It also makes sure the numbers stay correct
     * @param revenue The revenue that needs to be added
     */
    public void addRevenue(int revenue) {
        if (this.revenue == -1) this.revenue = revenue;
        else this.revenue += revenue;
    }

    /**
     * Adds the budget to the object
     * It also makes sure the numbers stay correct
     * @param budget The budget that needs to be added
     */
    public void addBudget(int budget) {
        if (this.budget == -1) this.budget = budget;
        else this.budget += budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getBudget() {
        return budget;
    }

}