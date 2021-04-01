package com.nhlstenden;

import com.nhlstenden.entities.*;
import com.nhlstenden.entities.containers.Container;
import com.nhlstenden.parsers.*;
import com.nhlstenden.relations.ManyToMany;

/**
 * This is the the class which organises all the different parsers
 */
public class ImdbParser {

    private final Container<Movie> movies;
    private final Container<Actor> actors;
    private final Container<Genre> genres;
    private final Container<Rating> ratings;
    private final Container<Business> business;
    private final Container<Location> locations;

    private final ManyToMany<Movie, Actor> playsIn;
    private final ManyToMany<Movie, Genre> moviesGenres;
    private final ManyToMany<Movie, Location> moviesLocations;

    /**
     * This constructor creates some objects needed in the parser!
     */
    public ImdbParser() {
        movies = new Container<>();
        actors = new Container<>();
        genres = new Container<>();
        ratings = new Container<>();
        business = new Container<>();
        locations = new Container<>();

        playsIn = new ManyToMany<>();
        moviesGenres = new ManyToMany<>();
        moviesLocations = new ManyToMany<>();
    }

    /**
     * This function parses everything, but really everything. Might take a while!
     */
    public void parse() {
        // First parse all the movies and actors/actresses
        parseAllMovies();
        parseAllActorsActresses();

        // Parse all the genres
        Parser genreParser = new GenreParser(genres, movies, moviesGenres);
        genreParser.parse();

        // Last but not least parse all the countries
        Parser locationParser = new LocationParser(locations, movies, moviesLocations);
        locationParser.parse();
    }

    /**
     * This function writes all the created objects to the files
     */
    public void writeEverythingToTheFiles() {
        Writer writer = new Writer();
        writer.writeWritableList(movies.getWritableList(), "movies.csv");
        writer.writeWritableList(ratings.getWritableList(), "ratings.csv");
        writer.writeWritableList(business.getWritableList(), "business.csv");
        writer.writeWritableList(locations.getWritableList(), "locations.csv");
        writer.writeWritableList(genres.getWritableList(), "genres.csv");
        writer.writeWritableList(actors.getWritableList(), "actors.csv");

        writer.writeWritableList(moviesGenres.getWritableList(), "movies_genre.csv");
        writer.writeWritableList(moviesLocations.getWritableList(), "movies_locations.csv");
        writer.writeWritableList(playsIn.getWritableList(), "plays_in.csv");
    }

    /**
     * Parses everything that has to do with the movies
     */
    private void parseAllMovies() {
        MovieParser movieParser = new MovieParser(movies);
        movieParser.parse();

        Parser movieRunningTimeParser = new MovieRunningTimeParser(movies);
        movieRunningTimeParser.parse();

        Parser ratingsParser = new RatingsParser(movies, ratings);
        ratingsParser.parse();

        Parser businessParser = new BusinessParser(movies, business);
        businessParser.parse();
    }

    /**
     * Parses the actors and actresses
     */
    private void parseAllActorsActresses() {
        ActorParser actorsParser = new ActorParser("actors.list", actors, movies, playsIn, Actor.Gender.MALE, 0);
        actorsParser.parse();

        ActorParser actressesParser = new ActorParser("actresses.list", actors, movies, playsIn, Actor.Gender.FEMALE, actorsParser.getLastId());
        actressesParser.parse();
    }
}
