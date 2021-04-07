package com.nhlstenden.parsers;

import com.nhlstenden.CurrencyConverter;
import static com.nhlstenden.Regex.businessRegex;
import com.nhlstenden.entities.Business;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the budget and revenue from the business.list.
 */
public class BusinessParser extends LineByLineParser implements Parser {

    private final Container<Movie> movies;
    private final Container<Business> business;
    private BusinessValues businessValues;

    private final Pattern businessPattern;
    private CurrencyConverter currencyConverter;

    /**
     * This creates the object and takes as a parameter the movieMap
     * @param movies Movies object containing all the movies
     */
    public BusinessParser(Container<Movie> movies, Container<Business> business) {
        super("business.list");
        this.movies = movies;
        this.business = business;

        this.businessPattern = Pattern.compile(businessRegex);

        this.businessValues = new BusinessValues();
        try {
            this.currencyConverter = new CurrencyConverter();
        } catch (IOException e) {
            System.out.println("Can't parse the currencies. Not parsing the business list");
            this.currencyConverter = null;
        }

    }

    @Override
    protected void parseLine(String line) {
        if (currencyConverter == null || line.isEmpty()) return;
        String kindOfInfo = line.substring(0, 2);

        System.out.println(line);

        Matcher matcher = businessPattern.matcher(line);

        // Let's see what's on the line, now we know what to do
        if (matcher.find()) {
            switch (kindOfInfo) {
                case "--" -> {
                    updateMovie(businessValues);
                    businessValues = new BusinessValues();
                }
                case "MV" -> {
                    businessValues.setTitle(matcher.group("mvTitle"));
                    businessValues.setYear(matcher.group("mvYear").contains("?") ? -1 : Integer.parseInt(matcher.group("mvYear")));
                    businessValues.setMovieNamePerYear(matcher.group("movieNamePerYear"));
                }
                case "BT" -> {
                    int euros = (int) currencyConverter.convertToEur(matcher.group("btAmount"), matcher.group("btCurrency"));
                    if (euros != -1)
                        businessValues.addBudget(euros);
                }
                case "GR" -> {
                    int euros = (int) currencyConverter.convertToEur(matcher.group("grAmount"), matcher.group("grCurrency"));
                    if (euros != -1)
                        businessValues.addRevenue(euros);
                }
            }
        }
    }

    /**
     * This function updates all the information in the movie object if it exists
     * @param businessValues The BusinessValues object when the updated information
     */
    private void updateMovie(BusinessValues businessValues) {
        Movie movie = movies.find(Movie.getKey(businessValues.getTitle(), businessValues.getYear(), businessValues.getMovieNamePerYear()));
        if (movie != null) {
            Business b = new Business(movie, businessValues.getRevenue(), businessValues.getBudget());
            business.add(b);
        }
        else {
            System.out.println("Movie: " + businessValues.getTitle() + " wasn't in the movie map!");
        }
    }

}

/**
 * This is a small entity class with the information from the business.list
 */
class BusinessValues {

    private String title;
    private int revenue = -1;
    private int budget = -1;
    private int year;
    private String movieNamePerYear;

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

    public void setMovieNamePerYear(String movieNamePerYear) {
        this.movieNamePerYear = movieNamePerYear;
    }

    public String getMovieNamePerYear() {
        return movieNamePerYear;
    }

}