package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.Objects;

import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This class holds two HasId objects that are related
 * And returns the CSV rows for the CSV file
 * @param <FT>
 * @param <ST>
 */
public class RelatedObjects<FT extends HasId, ST extends HasId> implements Writable {
    private final FT firstObject;
    private final ST secondObject;

    /**
     * Takes the two relatedObjects in the parameter and assigns them to the instance variables
     * @param first The first object
     * @param second The second object
     */
    public RelatedObjects(FT first, ST second) {
        this.firstObject = first;
        this.secondObject = second;
    }

    /**
     * Returns the first object
     * @return The first object
     */
    public FT getFirstObject() {
        return firstObject;
    }

    /**
     * Returns the second object
     * @return The second object
     */
    public ST getSecondObject() {
        return secondObject;
    }

    /**
     * Returns true if the object in the parameters has the same first and secondObject
     * @param o The object to compare
     * @return true if the first- and secondObject are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedObjects<?, ?> that = (RelatedObjects<?, ?>) o;
        return Objects.equals(firstObject, that.firstObject) && Objects.equals(secondObject, that.secondObject);
    }

    /**
     * Returns the hash of the two objects together
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstObject, secondObject);
    }

    /**
     * Returns the CSV row of the two relatedObjects
     * @return the CSV row in String format
     */
    @Override
    public String toCSV() {
        return toCsvField(firstObject.getId()) + ',' +
                toCsvField(secondObject.getId());
    }

    /**
     * The header of the table
     * @return The header of the table
     */
    @Override
    public String getHeader() {
        return firstObject.getClass().getSimpleName().toLowerCase() + "_id," +
                secondObject.getClass().getSimpleName().toLowerCase() + "_id";
    }

}
