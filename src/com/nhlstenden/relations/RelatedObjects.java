package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;

import java.util.Objects;

import static com.nhlstenden.FormatMethods.toCsvField;

public class RelatedObjects<FT extends HasId, ST extends HasId> implements Writable {
    private final FT firstObject;
    private final ST secondObject;

    public RelatedObjects(FT first, ST second) {
        this.firstObject = first;
        this.secondObject = second;
    }

    public FT getFirstObject() {
        return firstObject;
    }

    public ST getSecondObject() {
        return secondObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedObjects<?, ?> that = (RelatedObjects<?, ?>) o;
        return Objects.equals(firstObject, that.firstObject) && Objects.equals(secondObject, that.secondObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstObject, secondObject);
    }

    @Override
    public String toCSV() {
        return toCsvField(firstObject.getId()) + ',' +
                toCsvField(secondObject.getId());
    }

    @Override
    public String getHeader() {
        return firstObject.getClass().getSimpleName().toLowerCase() + "_id," +
                secondObject.getClass().getSimpleName().toLowerCase() + "_id";
    }

}
