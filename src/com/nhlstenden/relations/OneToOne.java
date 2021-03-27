package com.nhlstenden.relations;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.HasId;

public class OneToOne {

    private final HasId relationWith;

    protected OneToOne(HasId relationWith) {
        this.relationWith = relationWith;
    }

    protected int getRelationId() {
        return relationWith.getId();
    }

    protected HasId getRelated() {
        return relationWith;
    }

    protected String getForeignKeyCSVField() {
        return FormatMethods.toCsvField(relationWith.getId()) + ',';
    }

}
