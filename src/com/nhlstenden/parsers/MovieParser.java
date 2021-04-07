package com.nhlstenden.parsers;

import static com.nhlstenden.Regex.moviesRegex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the movies from movies.list to different Movie objects
 */
public class MovieParser extends LineByLineParser implements Parser {

    private final Container<Movie> movies;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Creates the parser and loads the regex pattern
     * @param movies the Movies object with all the different movies
     */
    public MovieParser(Container<Movie> movies) {
        super("movies.list");

        this.movies = movies;
        this.pattern = Pattern.compile(moviesRegex);
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
            String movieNamePerYear = matcher.group("movieNamePerYear");
            String mediaType = matcher.group("mediaType") == null ? "" : matcher.group("mediaType");

            // All of the checked value need to be zero before we know it's a movie!
            if (matcher.group("seriesPeriod") == null && matcher.group("suspended") == null &&
                    matcher.group("seriesEpisodeName") == null && matcher.group("seriesSeason") == null &&
                    matcher.group("endYear") == null && !mediaType.equals("VG")) {
                if (year.contains("?")) year = "-1";
                movies.add(new Movie(++idCounter, title, Integer.parseInt(year), movieNamePerYear));
            }
        }
    }
}
