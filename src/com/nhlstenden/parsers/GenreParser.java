package com.nhlstenden.parsers;

import com.nhlstenden.entities.Genre;
import com.nhlstenden.entities.Movie;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the genres from the genres.list
 */
public class GenreParser extends LineByLineParser {

    private final Map<String, Genre> genreMap;
    private final Map<String, Movie> movieMap;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Creates the object and takes the required parameters for a good working object
     * @param genreMap The map where the genres needs to be added
     * @param movieMap The map which contains all the movies
     */
    public GenreParser(Map<String, Genre> genreMap, Map<String, Movie> movieMap) {
        this.genreMap = genreMap;
        this.movieMap = movieMap;
        // TODO: add regex!
        this.pattern = Pattern.compile("^\\\"?(.*?)\\\"?\\s+\\((.{4}|.{4}\\/.+)\\)\\s?(?:(?!\\{.*\\(\\#)\\{.*\\})?\\t+(.*)\n");
    }

    /**
     * This function parses one line of the genre.list file
     * @param line The line which needs to be parsed
     */
    @Override
    protected void parseLine(String line) {
        // Let's execute the regex!
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            // Get the data from the matcher
            String title = matcher.group(1);
            String genreString = matcher.group(3);

            // If the title and genreString isn't null the regex gave back the information we need
            if (title != null && genreString != null) {
                // Let's check if the genre already exists, otherwise create the genre
                Genre genre;
                if (!genreMap.containsKey(title)) {
                    genre = new Genre(++idCounter, genreString);
                    genreMap.put(genreString, genre);
                }
                else
                    genre = genreMap.get(genreString);

                // If the movieMap contains the title we'll add this genres to it
                if (movieMap.containsKey(title)) {
                    Movie movie = movieMap.get(title);
                    movie.addGenre(genre);
                    genre.addMovie(movie);
                }
            }
        }
    }
}
