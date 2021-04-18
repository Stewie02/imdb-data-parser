package com.nhlstenden.parsers;

import static com.nhlstenden.Regex.genreRegex;
import com.nhlstenden.entities.Genre;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;
import com.nhlstenden.relations.ManyToMany;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the genres from the genres.list
 */
public class GenreParser extends LineByLineParser {

    private final Container<Movie> movies;
    private final Container<Genre> genres;
    private final ManyToMany<Movie, Genre> moviesGenres;

    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * Creates the object and takes the required parameters for a good working object
     * @param genres The container where the genres needs to be added
     * @param movies The container which contains all the movies
     * @param moviesGenres The ManyToMany object where the relations between movies and genres are stored
     */
    public GenreParser(Container<Genre> genres, Container<Movie> movies, ManyToMany<Movie, Genre> moviesGenres) {
        super("genres.list");
        this.genres = genres;
        this.movies = movies;
        this.moviesGenres = moviesGenres;

        this.pattern = Pattern.compile(genreRegex);
    }

    /**
     * This function parses one line of the genre.list file
     * @param line The line which needs to be parsed
     */
    @Override
    protected void parseLine(String line) {
        System.out.println(line);

        // Let's execute the regex!
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            // Get the data from the matcher
            String title = matcher.group("title");
            String year = matcher.group("year");
            String genreString = matcher.group("genre");
            String movieNamePerYear = matcher.group("movieNamePerYear");

            // If the title and genreString isn't null the regex gave back the information we need
            if (title != null && genreString != null) {
                Movie movie = movies.find(Movie.getKey(title, year, movieNamePerYear));
                if (movie == null) return;

                // Let's find the genre, if it doesn't exist we'll create the object
                Genre genre = genres.find(Genre.getKey(genreString));
                if (genre == null) {
                    genre = new Genre(++idCounter, genreString);
                    genres.add(genre);
                }

                // Add the two relatedObjects
                moviesGenres.addRelatedObjects(movie, genre);
            }
        }
    }
}
