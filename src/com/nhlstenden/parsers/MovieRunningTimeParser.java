package com.nhlstenden.parsers;

import com.nhlstenden.entities.Movie;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Get's the running time from the running-time.list file and adds it to the right Movie
 */
public class MovieRunningTimeParser extends LineByLineParser implements Parser {

    private final Map<String, Movie> movieMap;
    private final Pattern pattern;

    /**
     * Takes in the movieMap, this object can put the right running-time to the right Movie object
     * @param movieMap map containing all the different movies
     */
    public MovieRunningTimeParser(Map<String, Movie> movieMap) {
        super();
        this.movieMap = movieMap;
        this.fileName = "running-times.list";

        // TODO: Add to REGEX
        this.pattern = Pattern.compile("(?:(\\\"?(.+)\\\"?)\\s+?\\((.{4}|.{4}\\/.+)\\)\\s?(?:(?!\\{.*\\(\\#)\\{.*\\})?\\t+((\\d+)|.*?\\:(\\d+))\\s+?(?!\\())\n");
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
            // Get the running time out of the matcher
            String title = matcher.group(2);
            String runningTimeString = matcher.group(5) == null ? matcher.group(6) : matcher.group(5);
            int runningTime = Integer.parseInt(runningTimeString);

            // If the movieMap has the title we'll add the running time to it!
            if (movieMap.containsKey(title))
                movieMap.get(title).setRunningTime(runningTime);
            else
                System.out.println("Movie: " + title + " doesn't exist!");
        }
    }


}
