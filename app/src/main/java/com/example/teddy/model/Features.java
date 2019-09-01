package com.example.teddy.model;

import com.example.teddy.Attributes;
import com.example.teddy.Geometry;

import java.util.List;

public class Features {
    private List<Attributes> attributeObject;
    private List<Geometry> geometryObject;

    public List<Attributes> getAttributeObject() {
        return attributeObject;
    }

    public void setAttributeObject(List<Attributes> attributeObject) {
        this.attributeObject = attributeObject;
    }

    public List<Geometry> getGeometryObject() {
        return geometryObject;
    }

    public void setGeometryObject(List<Geometry> geometryObject) {
        this.geometryObject = geometryObject;
    }


    public Features(List<Attributes> attributeObject, List<Geometry> geometryObject) {
        this.attributeObject = attributeObject;
        this.geometryObject = geometryObject;
    }

    public Features(){}
}
