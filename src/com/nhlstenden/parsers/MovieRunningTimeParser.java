package com.nhlstenden.parsers;

import com.nhlstenden.FormatMethods;
import static com.nhlstenden.Regex.runningTimeRegex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Get's the running time from the running-time.list file and adds it to the right Movie
 */
public class MovieRunningTimeParser extends LineByLineParser implements Parser {

    private final Container<Movie> movies;
    private final Pattern pattern;

    /**
     * Takes in the movieMap, this object can put the right running-time to the right Movie object
     * @param movies Movies object containing all the different movies
     */
    public MovieRunningTimeParser(Container<Movie> movies) {
        super("running-times.list");
        this.movies = movies;
        this.pattern = Pattern.compile(runningTimeRegex);
    }

    /**
     * This function parses the line and add to running-time to the right movie
     * @param line The line to parse
     */
    @Override
    protected void parseLine(String line) {
        // Execute the regex
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            // If the seriesEpisodeName or seriesSeason equals null it's not a movie, so we will return
            if (matcher.group("seriesEpisodeName") != null || matcher.group("seriesSeason") != null) return;

            // Get the running time out of the matcher
            String title = matcher.group("title");
            int year = Integer.parseInt(matcher.group("year").contains("?") ? "-1" : matcher.group("year"));
            String runningTimeString = matcher.group("time");
            String movieNamePerYear = matcher.group("movieNamePerYear");

            // It can be that the runningTime isn't in group time, if it isn't it probably is in group countryTime
            if (runningTimeString == null)
                runningTimeString = matcher.group("countryTime");
            if (runningTimeString == null) {
                System.out.println(line);
                return;
            }
            int runningTime = Integer.parseInt(runningTimeString);

            // If the Movies contains the Movie we'll add the running time to it!
            Movie movie = movies.find(Movie.getKey(title, year, movieNamePerYear));
            if (movie != null) {
                movie.setRunningTime(runningTime);
            }
        }
    }


}
