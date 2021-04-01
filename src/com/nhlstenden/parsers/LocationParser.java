package com.nhlstenden.parsers;

import com.nhlstenden.entities.Location;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;
import com.nhlstenden.relations.ManyToMany;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nhlstenden.Regex.locationRegex;

/**
 * This class parses the countries from the locations.list.
 */
public class LocationParser extends LineByLineParser {

    private final Container<Location> locations;
    private final Container<Movie> movies;
    private final ManyToMany<Movie, Location> moviesLocations;

    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Takes the different objects which this parser needs.
     * @param locations Map where the countries will be added
     * @param movies The Container object which holds all the movies
     * @param moviesLocations The ManyToMany object with all the related movies and locations
     */
    public LocationParser(Container<Location> locations, Container<Movie> movies, ManyToMany<Movie, Location> moviesLocations) {
        super("locations.list");
        this.locations = locations;
        this.movies = movies;
        this.moviesLocations = moviesLocations;

        this.pattern = Pattern.compile(locationRegex);
    }

    /**
     * Parse a single line
     * @param line The line that's needs to be parsed
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            // Get the information from the regex
            String title = matcher.group("title");
            String year = matcher.group("year");
            String movieNamePerYear = matcher.group("movieNamePerYear");
            LocationInformation locationInformation = getLocationInformationFromMatcher(matcher);


            // If the regex didn't gave all the information back we don't want to use the line
            if (title != null) {

                // Let's grab the Movie from the container
                Movie movie = movies.find(Movie.getKey(title, year, movieNamePerYear));
                if (movie == null) {
                    System.out.println("Loc Movie " + title + " doesn't exist!");
                    return;
                }

                // If the location already exists, grab it from the container otherwise create a new one
                Location location = locations.find(Location.getKey(locationInformation.country, locationInformation.city));
                if (location == null)
                    location = new Location(++idCounter, locationInformation.city, locationInformation.country);

                locations.add(location);
                moviesLocations.addRelatedObjects(movie, location);

            }
        }

    }

    private LocationInformation getLocationInformationFromMatcher(Matcher matcher) {
        String city = "";
        String country = "";
        int i = 1;
        for (; i < 6; i++) {
            if (matcher.group("locationInfo" + i) == null)
                break;
        }
        switch (i -1) {
            case 1 -> country = matcher.group("locationInfo1");
            case 2 -> country = matcher.group("locationInfo2");
            case 3 -> {
                city = matcher.group("locationInfo1");
                country = matcher.group("locationInfo3");
            }
            case 4 -> {
                city = matcher.group("locationInfo2");
                country = matcher.group("locationInfo4");
            }
            case 5 -> {
                city = matcher.group("locationInfo3");
                country = matcher.group("locationInfo5");
            }
        }

        if (city.matches(".*\\d.*"))
            city = "";
        if (country.matches(".*\\d.*"))
            country = "";

        return new LocationInformation(city, country);
    }
}

class LocationInformation {
    public String country;
    public String city;

    public LocationInformation(String city, String country) {
        this.city = city;
        this.country = country;
    }
}