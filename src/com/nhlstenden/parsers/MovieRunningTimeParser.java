package com.nhlstenden.parsers;

import com.nhlstenden.Regex;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Get's the running time from the running-time.list file and adds it to the right Movie
 */
public class MovieRunningTimeParser extends LineByLineParser implements Parser {

    private final Movies movies;
    private final Pattern pattern;

    /**
     * Takes in the movieMap, this object can put the right running-time to the right Movie object
     * @param movies Movies object containing all the different movies
     */
    public MovieRunningTimeParser(Movies movies) {
        super();
        this.movies = movies;
        this.fileName = "running-times.list";

        // TODO: Add to REGEX
        this.pattern = Pattern.compile(Regex.runningTimeRegex);
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
            // If the seriesEpisodeName doesn't equal null it's a movie, so we will continue
            String seriesEpisodeName = matcher.group(5);
            if (seriesEpisodeName != null) return;

            // Get the running time out of the matcher
            String title = matcher.group(1);
            String year = matcher.group(2);
            String runningTimeString = matcher.group(10);

            // It can be that the runningTime isn't in group 10, if it isn't it probably is in group 13
            if (runningTimeString == null)
                runningTimeString = matcher.group(13);
            int runningTime = Integer.parseInt(runningTimeString);

            // If the Movies contains the Movie we'll add the running time to it!
            Movie movie = movies.findMovie(title, year);
            if (movie != null) {
                movie.setRunningTime(runningTime);
                System.out.println("Found the movie!");
            }
            else
                System.out.println("Movie: " + title + " doesn't exist!");
        }
    }


}
