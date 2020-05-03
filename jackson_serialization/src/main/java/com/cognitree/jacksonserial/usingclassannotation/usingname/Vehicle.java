package com.cognitree.jacksonserial.usingclassannotation.usingname;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "Car"),
        @JsonSubTypes.Type(value = Truck.class, name = "Truck"),
})
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