package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.relations.OneToOne;

import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * The rating object has the average given by the voters and the amount of votes
 * The object also has a OneToOne relationship with a movie
 */
public class Rating implements Entity {

    private final OneToOne<Movie> oneToOneRelation;

    private final double rating;
    private final int votes;

    /**
     * Creates the object and assigns the values in the parameters to it
     * @param relationWith The related Movie
     * @param rating The average rating
     * @param votes The amount of votes
     */
    public Rating(Movie relationWith, double rating, int votes) {
        this.oneToOneRelation = new OneToOne<>(relationWith);
        this.rating = rating;
        this.votes = votes;
    }

    /**
     * Return The CSV formatted String for this object
     * @return The CSV formatted String
     */
    @Override
    public String toCSV() {
        return oneToOneRelation.getForeignKeyCSVField() + ',' +
                toCsvField(rating) + ',' +
                toCsvField(votes);
    }

    /**
     * Returns the header of the object in CSV format
     * @return the header in CSV format
     */
    @Override
    public String getHeader() {
        return "movie_id,rating,votes";
    }

    /**
     * Returns the key of the object
     * @return The EntityKey of the object
     */
    @Override
    public EntityKey getKey() {
        return Rating.getKey(oneToOneRelation.getRelated());
    }

    /**
     * Returns the entityKey of a movie
     * @param movie The movie to get the EntityKey of
     * @return The EntityKey
     */
    public static EntityKey getKey(Movie movie) {
        return movie.getKey();
    }

}
