package com.nhlstenden.entities.containers;

import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.EntityKey;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.*;

/**
 * This class holds a lot of entities and stores them in a map
 * As key in the map it uses a EntityKey
 * With the function getWritableList() we get a list with all the writable entities
 * @param <T>
 */
public class Container <T extends Entity> {

    public Map<EntityKey, T> map;

    /**
     * Just initializing the map
     */
    public Container() {
        map = new HashMap<>();
    }

    /**
     * Give a EntityKey and we'll find the right Entity
     * @param key The key which equals the key of the object we want to find
     * @return The entity which corresponds with the key
     */
    public T find(EntityKey key) {
        return map.get(key);
    }

    /**
     * Adds an Entity to the Container
     * @param entity Entity to add to the container
     */
    public void add(T entity) {
        map.put(entity.getKey(), entity);
    }

    /**
     * Returns the list with all the Writable entities from the map
     * @return List with all the writable entities from the map
     */
    public List<Writable> getWritableList() {
        return new ArrayList<>(map.values());
    }
}
