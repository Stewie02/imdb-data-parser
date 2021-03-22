package com.nhlstenden.parsers;

import com.nhlstenden.entities.Movie;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the ratings.lists file and adds the rating to the right Movie object
 */
public class RatingsParser extends LineByLineParser {

    private final Pattern pattern;
    private final Map<String, Movie> movieMap;

    /**
     * Creates the parser object and initializes the pattern
     * @param movieMap map containing all the movies
     */
    public RatingsParser(Map<String, Movie> movieMap) {
        this.movieMap = movieMap;
        this.fileName = "ratings.list";
        pattern = Pattern.compile("(\\d\\.\\d)\\s*(.+)\\s\\(.*\\)");
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

            // Let's check if the movie with the title exists, if so set the right rating
            if (movieMap.containsKey(title))
                movieMap.get(title).setRating(rating);
            else
                System.out.println("Movie: " + title + " doesn't exist!");
        }
    }

}
