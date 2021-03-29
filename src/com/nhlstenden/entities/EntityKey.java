package com.nhlstenden.entities;

public class EntityKey {

    private final String key;

    public EntityKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityKey)
            return this.key.equals(((EntityKey) obj).getKey());
        return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public String getKey() {
        return key;
    }

}