package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.interfaces.HasId;

import static com.nhlstenden.FormatMethods.toCsvField;

public class Location implements Entity, HasId {

    private final int id;
    private final String city;
    private final String country;

    public Location(int id, String city, String country) {
        this.id = id;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toCSV() {
        return toCsvField(id) + ',' +
                toCsvField(country) + ',' +
                toCsvField(city);
    }

    @Override
    public String getHeader() {
        return "id,country,city";
    }

    @Override
    public EntityKey getKey() {
        return Location.getKey(country, city);
    }

    @Override
    public int getId() {
        return this.id;
    }

    public static EntityKey getKey(String country, String city) {
        return new EntityKey(city + country);
    }

}
