package com.nhlstenden.entities;

import com.nhlstenden.relations.OneToOne;

import static com.nhlstenden.FormatMethods.toCsvField;

public class Rating implements Entity {

    private final OneToOne oneToOneRelation;

    private double rating = -1;
    private int votes = 0;

    public Rating(Movie relationWith) {
        this.oneToOneRelation = new OneToOne(relationWith);
    }

    public Rating(Movie relationWith, double rating, int votes) {
        this(relationWith);
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
        return "movie_id,rates,votes";
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

}
