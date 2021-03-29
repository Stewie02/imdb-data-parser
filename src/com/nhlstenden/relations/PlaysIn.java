package com.nhlstenden.relations;

import com.nhlstenden.entities.Actor;
import com.nhlstenden.entities.Movie;

public class PlaysIn extends RelatedObjects<Movie, Actor> {

    private final String playsAs;

    public PlaysIn(Movie first, Actor second, String playsAs) {
        super(first, second);
        this.playsAs = playsAs;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ',' +
                this.playsAs;
    }

    @Override
    public String getHeader() {
        return super.getHeader() + ',' +
                "plays_as";
    }

}
