package com.nhlstenden;

import com.nhlstenden.entities.*;
import com.nhlstenden.parsers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the the class which organises all the different parsers
 */
public class ImdbParser {

    private final Movies movies;
    private final Map<String, Genre> genreMap;
    private final Map<String, Country> countryMap;

    private final List<Actor> actorList;
    private final List<Actor> actressesList;

    /**
     * This constructor creates some objects needed in the parser!
     */
    public ImdbParser() {
        movies = new Movies();
        genreMap = new HashMap<>();
        countryMap = new HashMap<>();

        actorList = new ArrayList<>();
        actressesList = new ArrayList<>();
    }

    /**
     * This function parses everything, but really everything. Might take a while!
     */
    public void parse() {
        // First parse all the movies and actors/actresses
        parseAllMovies();
        parseAllActorsActresses();

        // Parse all the genres
        Parser genreParser = new GenreParser(genreMap, movies);
        genreParser.parse();

        // Last but not least parse all the countries
        Parser countryParser = new CountryParser(countryMap, movies);
        countryParser.parse();
    }

    /**
     * This function writes all the created objects to the files
     */
    public void writeEverythingToTheFiles() {
        Writer writer = new Writer();
        writer.writeMovies(movies.getMap(), "movie.csv");

        writer.writeActors(actorList, "actor.csv", "actor_movie.csv");
        writer.writeActors(actressesList, "actress.csv", "actress_movie.csv");
        writer.writeGenres(genreMap, "genre.csv", "genre_movie.csv");
        writer.writeCountries(countryMap, "country.csv", "country_movie.csv");
    }

    /**
     * Parses everything that has to do with the movies
     */
    private void parseAllMovies() {
        MovieParser movieParser = new MovieParser(movies);
        movieParser.parse();
        System.out.println("Parsed the movies.list");

        Parser movieRunningTimeParser = new MovieRunningTimeParser(movies);
        movieRunningTimeParser.parse();

        Parser ratingsParser = new RatingsParser(movies);
        ratingsParser.parse();

        Parser businessParser = new BusinessParser(movies);
        businessParser.parse();
    }

    /**
     * Parses the actors and actresses
     */
    private void parseAllActorsActresses() {
        ActorParser actorsParser = new ActorParser("actors.list", actorList, movies);
        actorsParser.parse();

        ActorParser actressesParser = new ActorParser("actresses.list", actressesList, movies);
        actressesParser.parse();
    }

}
