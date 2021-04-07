package com.nhlstenden.relations;

import com.nhlstenden.entities.Actor;
import com.nhlstenden.entities.Movie;
import static com.nhlstenden.FormatMethods.toCsvField;

public class PlaysIn extends RelatedObjects<Movie, Actor> {

    private final String playsAs;

    public PlaysIn(Movie first, Actor second, String playsAs) {
        super(first, second);
        this.playsAs = playsAs;
    }

    @Override
    public String toCSV() {
        String end = "";
        if (this.playsAs.equals(""))
            end += "\\N";
        return super.toCSV() + ',' +
                toCsvField(this.playsAs) + end;
    }

    @Override
    public String getHeader() {
        return super.getHeader() + ',' +
                "plays_as";
    }

}
