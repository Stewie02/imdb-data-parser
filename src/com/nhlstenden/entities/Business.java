package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.relations.OneToOne;
import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This class has a OneToOne relationship with a Movie
 * And it holds the revenue and budget of a Movie
 */
public class Business implements Entity {

    private final OneToOne<Movie> oneToOneRelation;
    private final int revenue;
    private final int budget;

    /**
     * Create the object and assign the right values
     * @param relatedMovie The Movie where the Business object is related to
     * @param revenue The amount of revenue the Movie has
     * @param budget The amount of budget the Movie had
     */
    public Business(Movie relatedMovie, int revenue, int budget) {
        this.oneToOneRelation = new OneToOne<>(relatedMovie);
        this.revenue = revenue;
        this.budget = budget;
    }

    /**
     * Returns the CSV string of the object
     * @return The CSV string
     */
    @Override
    public String toCSV() {
        return oneToOneRelation.getForeignKeyCSVField() + ',' +
                toCsvField(budget) + ',' +
                toCsvField(revenue);
    }

    /**
     * This returns the header of the objects in CSV format
     * @return the header in CSV format
     */
    @Override
    public String getHeader() {
        return "movie_id,budget,revenue";
    }

    /**
     * Returns the key of the Business object
     * @return The key of the object
     */
    @Override
    public EntityKey getKey() {
        return Business.getKey(oneToOneRelation.getRelated());
    }

    /**
     * Returns the key of a movie
     * @param movie The Movie where you want the key of
     * @return The key of the Movie
     */
    public static EntityKey getKey(Movie movie) {
        return movie.getKey();
    }

}
