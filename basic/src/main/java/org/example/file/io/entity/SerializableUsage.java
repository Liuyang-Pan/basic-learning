package org.example.file.io.entity;

import java.io.Serial;
import java.io.Serializable;

public class SerializableUsage implements Serializable {
    @Serial
    private static final long serialVersionUID = -4721937280879295800L;
    private int intVar;
    private double doubleVar;
    //transient成员变量不参与序列化
    private transient String stringVar;

    public SerializableUsage() {
    }

    public SerializableUsage(int intVar, double doubleVar, String stringVar) {
        this.intVar = intVar;
        this.doubleVar = doubleVar;
        this.stringVar = stringVar;
    }

    public int getIntVar() {
        return intVar;
    }

    public void setIntVar(int intVar) {
        this.intVar = intVar;
    }

    public double getDoubleVar() {
        return doubleVar;
    }

    public void setDoubleVar(double doubleVar) {
        this.doubleVar = doubleVar;
    }

    public String getStringVar() {
        return stringVar;
    }

    public void setStringVar(String stringVar) {
        this.stringVar = stringVar;
    }

    @Override
    public String toString() {
        return "SerializableUsage{" +
                "intVar=" + intVar +
                ", doubleVar=" + doubleVar +
                ", stringVar='" + stringVar + '\'' +
                '}';
    }
}
