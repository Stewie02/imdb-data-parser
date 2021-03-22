package com.nhlstenden.parsers;

import com.nhlstenden.entities.Country;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the countries from the locations.list.
 */
public class CountryParser extends LineByLineParser {

    private final Map<String, Country> countryMap;
    private final Movies movies;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Takes the different objects which this parser needs.
     * @param countryMap Map where the countries will be added
     * @param movies The Movies object with all the movies
     */
    public CountryParser(Map<String, Country> countryMap, Movies movies) {
        this.countryMap = countryMap;
        this.movies = movies;
        this.fileName = "locations.list";

        // TODO: add regex!
        this.pattern = Pattern.compile("\\\"?(.*[^\\\"])\\\"?\\s\\([0-9]{4}\\)(?!\\{).+\\s(\\w+)(?:\\n|\\t)");
    }

    /**
     * Parse a single line
     * @param line The line that's needs to be parsed
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);

        // Get the information from the regex
        String title = matcher.group(1);
        String countryString = matcher.group(2);
        String year = matcher.group(3);

        // If the regex didn't gave all the information back we don't want to use the line
        if (title != null && countryString != null) {

            // If the country already exists, grab it from the map otherwise create a new one
            Country country;
            if (!countryMap.containsKey(countryString)) {
                country = new Country(++idCounter, countryString);
                countryMap.put(countryString, country);
            }
            else
                country = countryMap.get(countryString);

            // If the movie exists in the movieMap we'll add the objects to each other
            Movie movie = movies.findMovie(title, year);
            if (movie != null) {
                country.addMovie(movie);
                movie.addCountry(country);
            }

        }
    }
}
