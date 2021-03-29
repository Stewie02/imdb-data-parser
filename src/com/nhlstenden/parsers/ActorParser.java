package com.nhlstenden.parsers;

import com.nhlstenden.entities.Actor;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;
import com.nhlstenden.relations.ManyToMany;
import com.nhlstenden.relations.PlaysIn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nhlstenden.Regex.actorRegex;

/**
 * This class parses the actors.list file to all different objects
 */
public class ActorParser extends LineByLineParser {

    private final Container<Actor> actors;
    private final Container<Movie> movies;
    private final ManyToMany<Movie, Actor> playsIn;

    private Actor currentActor;
    private final Actor.Gender gender;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * This constructor creates the object and also compiles the regex pattern needed to compile the lines
     * @param file The name of the file
     * @param actors The Container object of actors where the actors will be added to
     * @param movies The Container object with all the movies
     * @param playsIn The ManyToMany object with all the relations between movies and actors
     */
    public ActorParser(String file, Container<Actor> actors, Container<Movie> movies, ManyToMany<Movie, Actor> playsIn, Actor.Gender gender) {
        super(file);
        this.actors = actors;
        this.movies = movies;
        this.playsIn = playsIn;
        this.gender = gender;

        this.pattern = Pattern.compile(actorRegex);
    }

    @Override
    protected void parseLine(String line) {
        // If the line is empty we immediately return
        if (line.isEmpty()) return;

        // Execute the regex
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String lastName = matcher.group("lastName");
            String firstName = matcher.group("firstName");
            String title = matcher.group("title");
            String year = matcher.group("year");
            String movieNamePerYear = matcher.group("movieNamePerYear");

            if (matcher.group("seriesEpisodeName") != null) return;

            Movie movie = movies.find(Movie.getKey(title, year, movieNamePerYear));
            if (movie == null) {
                System.out.println("Actor can't find movie: " + title);
                return;
            }

            // A new actor
            if (firstName != null) {
                if (lastName == null) lastName = "";
                currentActor = new Actor(++idCounter, firstName, lastName, gender);
            }

            actors.add(currentActor);
            this.playsIn.addRelatedObjects(new PlaysIn(movie, currentActor, matcher.group("characterName")));
        }
    }

}
