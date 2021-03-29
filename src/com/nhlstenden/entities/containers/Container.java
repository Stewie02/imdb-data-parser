package com.nhlstenden.entities.containers;

import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.EntityKey;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.*;

public class Container <T extends Entity> {

    public Map<EntityKey, T> map;

    public Container() {
        map = new HashMap<>();
    }

    private Container(Map<EntityKey, T> map) {
        this.map = map;
    }

    public T find(EntityKey key) {
        return map.get(key);
    }

    public void add(T entity) {
        if (map.containsKey(entity.getKey()))
            System.out.println("Key is the same!! " + entity.getKey());
        map.put(entity.getKey(), entity);
    }

    public List<Writable> getWritableList() {
        return new ArrayList<>(map.values());
    }
}
