package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.relations.OneToOne;

import static com.nhlstenden.FormatMethods.toCsvField;

public class Rating implements Entity {

    private final OneToOne<Movie> oneToOneRelation;

    private final double rating;
    private final int votes;

    public Rating(Movie relationWith, double rating, int votes) {
        this.oneToOneRelation = new OneToOne<>(relationWith);
        this.rating = rating;
        this.votes = votes;
    }

    @Override
    public String toCSV() {
        return oneToOneRelation.getForeignKeyCSVField() + ',' +
                toCsvField(rating) + ',' +
                toCsvField(votes);
    }

    @Override
    public String getHeader() {
        return "movie_id,rating,votes";
    }

    @Override
    public EntityKey getKey() {
        return Rating.getKey(oneToOneRelation.getRelated());
    }

    public static EntityKey getKey(Movie movie) {
        return movie.getKey();
    }

}
