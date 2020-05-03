package com.cognitree.jacksonserial.usingclassannotation.usingminamalclass;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "className")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Car.class),
//        @JsonSubTypes.Type(value = Truck.class),
//})
public abstract class Vehicle {

    private String make;
    private String model;

    protected Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}