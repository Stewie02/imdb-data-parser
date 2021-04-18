package com.nhlstenden.entities.interfaces;

public interface Writable {

    /**
     * Returns the CSV string of the object
     * @return the CSV string
     */
    String toCSV();

    /**
     * The header in CSV
     * @return The header
     */
    String getHeader();
}
