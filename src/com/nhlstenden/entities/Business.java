package com.nhlstenden.entities;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.relations.OneToOne;

public class Business implements Entity {

    private final OneToOne oneToOneRelation;
    private int revenue = -1;
    private int budget = -1;

    public Business(Movie relatedMovie) {
        this.oneToOneRelation = new OneToOne(relatedMovie);
    }

    @Override
    public String toCSV() {
        return oneToOneRelation.getForeignKeyCSVField() + ',' +
                FormatMethods.toCsvField(revenue) + ',' +
                FormatMethods.toCsvField(budget) + ',';
    }

    @Override
    public String getHeader() {
        return "movie_id,revenue,budget";
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
