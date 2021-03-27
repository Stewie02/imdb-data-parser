package com.nhlstenden.parsers;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.Regex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;
import com.nhlstenden.entities.Rating;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the ratings.lists file and adds the rating to the right Movie object
 */
public class RatingsParser extends LineByLineParser {

    private final Pattern pattern;
    private final Movies movies;
    private final List<Rating> ratings;

    /**
     * Creates the parser object and initializes the pattern
     * @param movies movies objects containing all the movies
     */
    public RatingsParser(Movies movies, List<Rating> ratings) {
        this.movies = movies;
        this.ratings = ratings;
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
            // If the seriesEpisodeName or seriesSeason doesn't equal null it's a movie, so we will return
            if (matcher.group("seriesEpisodeName") != null || matcher.group("seriesSeason") != null) return;

            // Get the data from the matcher
            double ratingValue = Double.parseDouble(matcher.group("rank"));
            int votes = Integer.parseInt(matcher.group("votes"));
            String title = matcher.group("title");
            String year = matcher.group("year");
            String movieNamePerYear = FormatMethods.getMovieNamePerYear(matcher);

            // Let's check if the movie with the title exists, if so set the right rating
            Movie movie = movies.findMovie(title, year, movieNamePerYear);
            if (movie != null) {
                Rating rating = new Rating(movie);
                rating.setRating(ratingValue);
                rating.setVotes(votes);
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
