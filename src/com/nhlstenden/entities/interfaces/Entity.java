package com.nhlstenden.entities.interfaces;

import com.nhlstenden.entities.EntityKey;

public interface Entity extends Writable {

    /**
     * This function return the CSV record of the object
     * @return The CSV record
     */
    String toCSV();

    /**
     * This function return the header of the record
     * @return The header
     */
    String getHeader();

    /**
     * Every Entity has a unique key which this function returns
     * @return It's unique key
     */
    EntityKey getKey();

}