package org.example.commonapis.object;

import java.util.Objects;

public class ObjectCommon {

    private String objectName;

    @Override
    public String toString() {
        return "ObjectCommon{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectCommon that = (ObjectCommon) o;
        return Objects.equals(objectName, that.objectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName);
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
