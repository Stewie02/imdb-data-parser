package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.interfaces.HasId;

import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * A Location is an object which holds the city and country
 * Movies can be played in Locations
 */
public class Location implements Entity, HasId {

    private final int id;
    private final String city;
    private final String country;

    /**
     * This created the object
     * @param id The id of the Location
     * @param city The city
     * @param country The country
     */
    public Location(int id, String city, String country) {
        this.id = id;
        this.city = city;
        this.country = country;
    }

    /**
     * This returns a CSV formatted String
     * @return The CSV formatted String
     */
    @Override
    public String toCSV() {
        return toCsvField(id) + ',' +
                toCsvField(country) + ',' +
                toCsvField(city);
    }

    /**
     * Return this header of the Location object
     * @return The header in CSV format
     */
    @Override
    public String getHeader() {
        return "id,country,city";
    }

    /**
     * This returns the EntityKey of the object
     * @return The EntityKey
     */
    @Override
    public EntityKey getKey() {
        return Location.getKey(country, city);
    }

    /**
     * Returns the id of the object
     * @return the id
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the EntityKey of a country and city, which are given in the parameters
     * @param country The country where you want the key in
     * @param city The city where you want the key in
     * @return The newly created EntityKey
     */
    public static EntityKey getKey(String country, String city) {
        return new EntityKey(city + country);
    }

}
