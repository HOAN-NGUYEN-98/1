package com.test.models;

import java.io.Serializable;

public class TypeBookRespone implements Serializable {
    String idType, nameType, describe, location;

    public TypeBookRespone() {

    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TypeBookRespone(String idType, String nameType, String describe, String location) {
        this.idType = idType;
        this.nameType = nameType;
        this.describe = describe;
        this.location = location;
    }
}
