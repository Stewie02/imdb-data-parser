package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.ArrayList;
import java.util.List;

public class ManyToMany<FT extends HasId, ST extends HasId> {

    private final List<RelatedObjects<FT,ST>> relatedObjects;

    public ManyToMany() {
        relatedObjects = new ArrayList<>();
    }

    public void addRelatedObjects(FT first, ST second) {
        relatedObjects.add(new RelatedObjects<>(first, second));
    }

    public void addRelatedObjects(RelatedObjects<FT, ST> relatedObjects) {
        this.relatedObjects.add(relatedObjects);
    }


    public String getHeader() {
        if (relatedObjects.size() == 0) return "";

        return relatedObjects.get(0).getFirstObject().getClass().getSimpleName().toLowerCase() + "_id," +
                relatedObjects.get(0).getSecondObject().getClass().getSimpleName().toLowerCase() + "_id";
    }

    public List<Writable> getWritableList() {
        return new ArrayList<>(relatedObjects);
    }

}

