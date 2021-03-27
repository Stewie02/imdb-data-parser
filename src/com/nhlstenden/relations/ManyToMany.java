package com.nhlstenden.relations;

import com.nhlstenden.FormatMethods;
import com.nhlstenden.entities.HasId;

public class ManyToMany {

    private final HasId firstObject;
    private final HasId secondObject;

    public ManyToMany(HasId firstObject, HasId secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public String toCsv() {
        return FormatMethods.toCsvField(firstObject.getId()) + ',' +
                FormatMethods.toCsvField(secondObject.getId()) + ',';
    }

    public String getHeader() {
        return firstObject.getClass().getSimpleName().toLowerCase() + "_id," +
                secondObject.getClass().getSimpleName().toLowerCase() + "_id";
    }

    public HasId getFirstObject() {
        return firstObject;
    }

    public HasId getSecondObject() {
        return secondObject;
    }
}
