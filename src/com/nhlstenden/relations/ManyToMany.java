package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This ManyToMany class takes in two types which both need an id
 * @param <FT>
 * @param <ST>
 */
public class ManyToMany<FT extends HasId, ST extends HasId> {

    private final Set<RelatedObjects<FT,ST>> relatedObjectsSet;

    /**
     * Initializes the HashSet
     */
    public ManyToMany() {
        relatedObjectsSet = new HashSet<>();
    }

    /**
     * Add the two related objects
     * @param first The first object
     * @param second The second object
     */
    public void addRelatedObjects(FT first, ST second) {
        relatedObjectsSet.add(new RelatedObjects<>(first, second));
    }

    /**
     * Add the relatedObject object
     * @param relatedObjects The object to add
     */
    public void addRelatedObjects(RelatedObjects<FT, ST> relatedObjects) {
        this.relatedObjectsSet.add(relatedObjects);
    }

    /**
     * Returns the header of table in CSV format
     * @return The header in CSV format
     */
    public String getHeader() {
        if (relatedObjectsSet.size() == 0) return "";
        RelatedObjects<FT, ST> relatedObjects = relatedObjectsSet.stream().findFirst().get();
        return relatedObjects.getFirstObject().getClass().getSimpleName().toLowerCase() + "_id," +
                relatedObjects.getSecondObject().getClass().getSimpleName().toLowerCase() + "_id";
    }

    /**
     * Return a list of the writable
     * @return The list
     */
    public List<Writable> getWritableList() {
        return new ArrayList<>(relatedObjectsSet);
    }

}