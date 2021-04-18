package com.nhlstenden.entities;

/**
 * The Entity is an object which holds a String
 * 2 EntityKeys are equal if the String is also equal
 * And the hashCode is the hashCode of the String
 * This object is used in the Container object
 */
public class EntityKey {

    private final String key;

    /**
     * Creates the EntityKey with the String in the parameters
     * @param key The string which is the key
     */
    public EntityKey(String key) {
        this.key = key;
    }

    /**
     * Returns true if the object in the parameters equals this one
     * Objects are equal if the key is equal
     * @param obj The key which we want to compare
     * @return a boolean  which is true if the objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityKey)
            return this.key.equals(((EntityKey) obj).getKey());
        return false;
    }

    /**
     * The hashCode of this object, this equals the hashCode of the String key
     * @return the hashCode of the object
     */
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Return the key
     * @return The key of this object
     */
    private String getKey() {
        return key;
    }

}