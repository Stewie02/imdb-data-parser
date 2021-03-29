package com.nhlstenden.relations;

import com.nhlstenden.entities.interfaces.HasId;
import com.nhlstenden.entities.interfaces.Writable;
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
