package com.nhlstenden.relations;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.interfaces.HasId;

/**
 * This class is for an object which holds a foreign key with another object
 * It holds the related object and makes it easier to get the right data from the related object
 * @param <T>
 */
public class OneToOne <T extends HasId> {

    private final T relationWith;

    /**
     * Assigns the parameters to instance variables
     * @param relationWith The related object
     */
    public OneToOne(T relationWith) {
        this.relationWith = relationWith;
    }

    /**
     * Get the id of the related object
     * @return The id of the related
     */
    public int getRelationId() {
        return relationWith.getId();
    }

    /**
     * Returns the related object
     * @return The related object
     */
    public T getRelated() {
        return relationWith;
    }

    /**
     * Returns the foreign key
     * @return The foreign key
     */
    public String getForeignKeyCSVField() {
        return FormatMethods.toCsvField(relationWith.getId());
    }

}
