package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.relations.OneToOne;
import static com.nhlstenden.FormatMethods.toCsvField;

public class Business implements Entity {

    private final OneToOne<Movie> oneToOneRelation;
    private final int revenue;
    private final int budget;

    public Business(Movie relatedMovie, int revenue, int budget) {
        this.oneToOneRelation = new OneToOne<>(relatedMovie);
        this.revenue = revenue;
        this.budget = budget;
    }

    @Override
    public String toCSV() {
        return oneToOneRelation.getForeignKeyCSVField() + ',' +
                toCsvField(budget) + ',' +
                toCsvField(revenue);
    }

    @Override
    public String getHeader() {
        return "movie_id,budget,revenue";
    }

    @Override
    public EntityKey getKey() {
        return Business.getKey(oneToOneRelation.getRelated());
    }

    public static EntityKey getKey(Movie movie) {
        return movie.getKey();
    }

}
