package com.nhlstenden.parsers;

import com.nhlstenden.entities.Actor;
import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.Movies;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the actors.list file to all different objects
 */
public class ActorParser extends LineByLineParser {

    private Actor currentActor;
    private final List<Actor> actorList;
    private final Movies movies;
    private final Pattern pattern;
    private int idCounter = 0;

    /**
     * This constructor creates the object and also compiles the regex pattern needed to compile the lines
     * @param file The name of the file
     * @param actorList The list of actors where the actors will be added
     * @param movies The Movies object with all the movies
     */
    public ActorParser(String file, List<Actor> actorList, Movies movies) {
        this.actorList = actorList;
        this.movies = movies;
        this.fileName = file;

        this.pattern = Pattern.compile("^(((.*?),\\s+)?(.*?))?\\t+(.*?)\\s+\\((.{4})\\)\\s+(?!\\{.*\\})(\\((as\\s+)?(.*)\\)\\s+)?(\\[(.*)\\])?");
    }

    @Override
    protected void parseLine(String line) {
        // If the line is empty we immediately return
        if (line.isEmpty()) return;

        // Execute the regex
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String lastName = matcher.group(3);
            String firstName = matcher.group(4);
            String movieName = matcher.group(5);
            String movieYear = matcher.group(6);

            // If the firstname isn't null it means we found a new actors so we'll create a new object
            if (firstName != null) {
                if (lastName == null) lastName = "";

                currentActor = new Actor(++idCounter, firstName, lastName);
                actorList.add(currentActor);
            }

            // Let's check if the Movie object exists
            // If it's the case we'll add the movie to the actors and vice versa
            Movie movie = movies.findMovie(movieName, movieYear);
            if (movie != null) {
                currentActor.addMovie(movie);
                movie.addActor(currentActor);
            }
        }
    }

}
