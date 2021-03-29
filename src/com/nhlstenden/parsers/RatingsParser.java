package com.nhlstenden.parsers;

import com.nhlstenden.FormatMethods;
import static com.nhlstenden.Regex.ratingsRegex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Rating;
import com.nhlstenden.entities.containers.Container;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the ratings.lists file and adds the rating to the right Movie object
 */
public class RatingsParser extends LineByLineParser {

    private final Pattern pattern;
    private final Container<Movie> movies;
    private final Container<Rating> ratings;

    /**
     * Creates the parser object and initializes the pattern
     * @param movies movies objects containing all the movies
     */
    public RatingsParser(Container<Movie> movies, Container<Rating> ratings) {
        super("ratings.list");
        this.movies = movies;
        this.ratings = ratings;
        pattern = Pattern.compile(ratingsRegex);
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
            // If the seriesEpisodeName or seriesSeason doesn't equal null it's a movie, so we will return
            if (matcher.group("seriesEpisodeName") != null || matcher.group("seriesSeason") != null) return;

            // Get the data from the matcher
            double ratingValue = Double.parseDouble(matcher.group("rank"));
            int votes = Integer.parseInt(matcher.group("votes"));
            String title = matcher.group("title");
            String year = matcher.group("year");
            String movieNamePerYear = matcher.group("movieNamePerYear");

            // Let's check if the movie with the title exists, if so set the right rating
            Movie movie = movies.find(Movie.getKey(title, year, movieNamePerYear));
            if (movie != null) {
                Rating rating = new Rating(movie, ratingValue, votes);
                ratings.add(rating);

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
