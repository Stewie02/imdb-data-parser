package com.nhlstenden.entities;

public interface Entity {

    /**
     * This function return the CSV record of the object
     * @return The CSV record
     */
    public abstract String toCSV();
}