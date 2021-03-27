package com.nhlstenden.parsers;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.Regex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the movies from movies.list to different Movie objects
 */
public class MovieParser extends LineByLineParser implements Parser {

    private final Movies movies;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Creates the parser and loads the regex pattern
     * @param movies the Movies object with all the different movies
     */
    public MovieParser(Movies movies) {
        this.movies = movies;
        this.fileName = "movies.list";
        this.pattern = Pattern.compile(Regex.moviesList);
    }

    /**
     * Parse every individual line and creates the right Movie object for it
     * @param line The line to parse
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            // Get the data out of the regex
            String title = matcher.group("title");
            String year = matcher.group("year");
            String movieNamePerYear = FormatMethods.getMovieNamePerYear(matcher);

            // All of the checked value need to be zero before we know it's a movie!
            if (matcher.group("seriesPeriod") == null && matcher.group("suspended") == null &&
                    matcher.group("seriesEpisodeName") == null && matcher.group("seriesSeason") == null &&
                    matcher.group("endYear") == null) {
                if (year.contains("?")) year = "-1";
                movies.addMovie(new Movie(++idCounter, title, Integer.parseInt(year), movieNamePerYear));
            }
        }
    }
}
