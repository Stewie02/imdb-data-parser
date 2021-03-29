package com.nhlstenden.relations;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.interfaces.HasId;

public class OneToOne <T extends HasId> {

    private final T relationWith;

    public OneToOne(T relationWith) {
        this.relationWith = relationWith;
    }

    public int getRelationId() {
        return relationWith.getId();
    }

    public T getRelated() {
        return relationWith;
    }

    public String getForeignKeyCSVField() {
        return FormatMethods.toCsvField(relationWith.getId());
    }

}
