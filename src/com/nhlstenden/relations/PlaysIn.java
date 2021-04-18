package com.nhlstenden.relations;

import com.nhlstenden.entities.Actor;
import com.nhlstenden.entities.Movie;
import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This class is an extension on RelatedObjects
 * This is because in the playsAs field is needed in this table
 */
public class PlaysIn extends RelatedObjects<Movie, Actor> {

    private final String playsAs;

    /**
     * Takes the Movie, Actor objects,String playsAs
     * And assign them to the instances variables
     * @param first The movie
     * @param second The actor
     * @param playsAs The name of the character
     */
    public PlaysIn(Movie first, Actor second, String playsAs) {
        super(first, second);
        this.playsAs = playsAs;
    }

    /**
     * Returns the CSV row of the object
     * @return The CSV
     */
    @Override
    public String toCSV() {
        String end = "";
        if (this.playsAs.equals(""))
            end += "\\N";
        return super.toCSV() + ',' +
                toCsvField(this.playsAs) + end;
    }

    /**
     * Returns the header of the table
     * @return The header of the table
     */
    @Override
    public String getHeader() {
        return super.getHeader() + ',' +
                "plays_as";
    }

}
