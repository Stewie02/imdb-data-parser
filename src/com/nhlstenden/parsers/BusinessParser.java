package com.nhlstenden.parsers;

import com.nhlstenden.entities.Movie;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the budget and revenue from the business.list.
 */
public class BusinessParser extends LineByLineParser implements Parser {

    private final Map<String, Movie> movieMap;
    private Business business;

    private final Pattern moviePattern;
    private final Pattern revenuePattern;
    private final Pattern budgetPattern;

    /**
     * This creates the object and takes as a parameter the movieMap
     * @param movieMap map containing all the movies
     */
    public BusinessParser(Map<String, Movie> movieMap) {
        this.movieMap = movieMap;
        this.fileName = "business.list";

        this.business = new Business();

        this.moviePattern = Pattern.compile("MV:\\s\\\"?(.*[^\\\"])\\\"?\\s\\([0-9\\/IV\\?]{4,}\\)\\s(?!\\{)\n");
        this.revenuePattern = Pattern.compile("GR:\\s([A-z]+)\\s([0-9,]+)\n");
        this.budgetPattern = Pattern.compile("BT:\\s([A-z]+)\\s([0-9,]+)\n");
    }

    @Override
    protected void parseLine(String line) {
        String kindOfInfo = line.substring(0, 2);

        // Let's see what's on the line, now we know what to do
        switch (kindOfInfo) {
            case "--" -> {
                updateMovie(business);
                business = new Business();
            }
            case "MV" -> business.setTitle(parseMovieTitle(line));
            case "BT" -> business.addBudget(parseMovieBudget(line));
            case "GR" -> business.addRevenue(parseMovieRevenue(line));
        }
    }

    /**
     * This function updates all the information in the movie object if it exists
     * @param business The business object when the updated information
     */
    private void updateMovie(Business business) {
        if (movieMap.containsKey(business.getTitle())) {
            Movie movie = movieMap.get(business.getTitle());
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
     * @return The movie title
     */
    private String parseMovieTitle(String line) {
        Matcher matcher = moviePattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
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

    public int getRevenue() {
        return revenue;
    }

    public int getBudget() {
        return budget;
    }

}