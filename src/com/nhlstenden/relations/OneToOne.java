package com.nhlstenden.relations;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.HasId;

public class OneToOne {

    private final HasId relationWith;

    public OneToOne(HasId relationWith) {
        this.relationWith = relationWith;
    }

    public int getRelationId() {
        return relationWith.getId();
    }

    public HasId getRelated() {
        return relationWith;
    }

    public String getForeignKeyCSVField() {
        return FormatMethods.toCsvField(relationWith.getId());
    }

}
