package com.nhlstenden.entities;

public interface Entity {

    /**
     * This function return the CSV record of the object
     * @return The CSV record
     */
    public abstract String toCSV();

    /**
     * This function return the header of the record
     * @return The header
     */
    public abstract String getHeader();

}