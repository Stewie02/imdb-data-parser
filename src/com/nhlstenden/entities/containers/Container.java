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

    public T find(EntityKey key) {
        return map.get(key);
    }

    public void add(T entity) {
        map.put(entity.getKey(), entity);
    }

    public List<Writable> getWritableList() {
        return new ArrayList<>(map.values());
    }
}
