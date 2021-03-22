package com.nhlstenden.parsers;

import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.Map;
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
        pattern = Pattern.compile("[\\d.*]{1,10}\\s+[\\d]{1,9}\\s+([\\d.]{3})\\s+(?:\\\"?(.*?)\\\"?)\\s\\(([\\d]{4}|\\?{4})?\\)\\s(?!\\{.*?\\})");
    }

    /**
     * Parses the individual line and add the rating to the right Movie object
     * @param line The line to parse
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            // Get the data from the matcher
            double rating = Double.parseDouble(matcher.group(1));
            String title = matcher.group(2);
            String year = matcher.group(3);

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
        }
    }

}
