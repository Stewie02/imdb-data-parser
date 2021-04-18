package com.nhlstenden.parsers;

import com.nhlstenden.CurrencyConverter;
import static com.nhlstenden.Regex.businessRegex;
import com.nhlstenden.entities.Business;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;

import java.io.IOException;
import java.util.Locale;
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

    private boolean usedTheBestRevenue = false;

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
            this.currencyConverter = null;
        }

    }

    @Override
    protected void parseLine(String line) {
        if (currencyConverter == null || line.isEmpty()) return;
        String kindOfInfo = line.substring(0, 2);
        Matcher matcher = businessPattern.matcher(line);

        // Let's see what's on the line, now we know what to do
        if (matcher.find()) {
            switch (kindOfInfo) {
                // There is a new movie, let's update the previous one and create a new BusinessValues object
                case "--" -> {
                    updateMovie(businessValues);
                    businessValues = new BusinessValues();
                }
                // Here is the title and other info about the
                case "MV" -> {
                    businessValues.setTitle(matcher.group("mvTitle"));
                    businessValues.setYear(matcher.group("mvYear").contains("?") ? -1 : Integer.parseInt(matcher.group("mvYear")));
                    businessValues.setMovieNamePerYear(matcher.group("movieNamePerYear"));
                    usedTheBestRevenue = false;
                }
                // We'll set the budget in the businessValues
                case "BT" -> {
                    int euros = (int) currencyConverter.convertToEur(matcher.group("btAmount"), matcher.group("btCurrency"));
                    if (euros > 0)
                        businessValues.setBudget(euros);
                }
                // We'll set the revenue in the businessValues
                case "GR" -> {
                    // If we already used the best value we'll go on
                    if (!usedTheBestRevenue) {
                        int rev = -1;
                        // If true we found the best value
                        if (matcher.group("grYear") == null && matcher.group("grInitials").toLowerCase(Locale.ROOT).equals("worldwide")) {
                            rev = (int) currencyConverter.convertToEur(matcher.group("grAmount"), matcher.group("grCurrency"));
                            usedTheBestRevenue = true;
                        }
                        // There is a value, perhaps not the best
                        else if (matcher.group("grInitials").toLowerCase(Locale.ROOT).equals("worldwide")) {
                            rev = (int) currencyConverter.convertToEur(matcher.group("grAmount"), matcher.group("grCurrency"));
                        }
                        // If we found a revenue value let's set it
                        if (rev > 0)
                            businessValues.setRevenue(rev);
                    }
                }
            }
        }

    }

    /**
     * This function updates all the information in the movie object if it exists
     * @param businessValues The BusinessValues object when the updated information
     */
    private void updateMovie(BusinessValues businessValues) {
        // Find the movie and if it isn't null we'll add it to the container
        Movie movie = movies.find(Movie.getKey(businessValues.getTitle(), businessValues.getYear(), businessValues.getMovieNamePerYear()));
        if (movie != null) {
            Business b = new Business(movie, businessValues.getRevenue(), businessValues.getBudget());
            business.add(b);
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
    public void setRevenue(int revenue) {
        this.revenue += revenue;
    }

    /**
     * Adds the budget to the object
     * It also makes sure the numbers stay correct
     * @param budget The budget that needs to be added
     */
    public void setBudget(int budget) {
        this.budget += budget;
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