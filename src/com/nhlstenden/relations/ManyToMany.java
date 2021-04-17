package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManyToMany<FT extends HasId, ST extends HasId> {

    private final Set<RelatedObjects<FT,ST>> relatedObjectsSet;

    public ManyToMany() {
        relatedObjectsSet = new HashSet<>();
    }

    public void addRelatedObjects(FT first, ST second) {
        relatedObjectsSet.add(new RelatedObjects<>(first, second));
    }

    public void addRelatedObjects(RelatedObjects<FT, ST> relatedObjects) {
        this.relatedObjectsSet.add(relatedObjects);
    }

    public String getHeader() {
        if (relatedObjectsSet.size() == 0) return "";
        RelatedObjects<FT, ST> relatedObjects = relatedObjectsSet.stream().findFirst().get();
        return relatedObjects.getFirstObject().getClass().getSimpleName().toLowerCase() + "_id," +
                relatedObjects.getSecondObject().getClass().getSimpleName().toLowerCase() + "_id";
    }

    public List<Writable> getWritableList() {
        return new ArrayList<>(relatedObjectsSet);
    }

}