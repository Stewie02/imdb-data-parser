package com.nhlstenden.parsers;

import com.nhlstenden.Regex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the ratings.lists file and adds the rating to the right Movie object
 */
public class RatingsParser extends LineByLineParser {

    private final Pattern pattern;
    private final Movies movies;

    /**
     * Creates the parser object and initializes the pattern
     * @param movies movies objects containing all the movies
     */
    public RatingsParser(Movies movies) {
        this.movies = movies;
        this.fileName = "ratings.list";
        pattern = Pattern.compile(Regex.ratingsRegex);
    }

    /**
     * Parses the individual line and add the rating to the right Movie object
     * @param line The line to parse
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            // If the seriesEpisodeName doesn't equal null it's a movie, so we will continue
            String seriesEpisodeName = matcher.group(5);
            if (seriesEpisodeName != null) return;

            // Get the data from the matcher
            double rating = Double.parseDouble(matcher.group(3));
            String title = matcher.group(4);
            String year = matcher.group(5);

            // Let's check if the movie with the title exists, if so set the right rating
            Movie movie = movies.findMovie(title, year);
            if (movie != null) {
                movie.setRating(rating);
                System.out.println("Set the rating for movie: " + title);
            }
            else
                System.out.println("Movie: " + title + " doesn't exist!");
        }
        else {
            System.out.println("Didn't match");
            System.out.println(line);
        }
    }

}
