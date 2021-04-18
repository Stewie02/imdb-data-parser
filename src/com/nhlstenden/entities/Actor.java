package com.nhlstenden.entities;

import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.interfaces.HasId;

import static com.nhlstenden.FormatMethods.toCsvField;

/**
 * This Actor class represents the actors that will be written to the CSV
 * It has some basic getters/setters and add functions
 */
public class Actor implements Entity, HasId {

    private final int id;
    private final String lastName;
    private final String firstName;
    private final Gender gender;

    /**
     * Creates the Actor object
     * @param id the id of this new object
     * @param firstName the firstname of this new object
     * @param lastName the lastname of this new object
     * @param gender the gender of the actor
     */
    public Actor(int id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Returns the string how the CSV record looks like
     * @return the CSV record of this object
     */
    @Override
    public String toCSV() {
        char genderChar = switch (gender) {
            case FEMALE -> 'F';
            case MALE -> 'M';
        };

        return toCsvField(id) + ',' +
                toCsvField(firstName) + ',' +
                toCsvField(lastName) + ',' +
                genderChar;
    }

    /**
     * Returns the EntityKey of the object
     * @return the EntityKey of the object
     */
    @Override
    public EntityKey getKey() {
        return Actor.getKey(id, firstName, lastName);
    }

    /**
     * The id of the Actor
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns the header of the CSV file
     * @return Header of the CSV file
     */
    public String getHeader() {
        return "id,first_name,last_name,gender";
    }

    /**
     * This function returns the EntityKey of a actor
     * @param id The id of the actor
     * @param firstName The firstName of the actor
     * @param lastName The lastName of the actor
     * @return The EntityKey
     */
    public static EntityKey getKey(int id, String firstName, String lastName) {
        return new EntityKey(id + firstName + lastName);
    }

    /**
     * This enum holds the genders which can occur in the database files
     */
    public enum Gender {
        FEMALE,
        MALE
    }
}
