package com.cognitree.jacksonserial.usingclassannotation.usingname;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Car")
public class Car extends Vehicle {

    private int seatingCapacity;
    private double topSpeed;

    @JsonCreator
    public Car(@JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("seatCapacity") int seatingCapacity, @JsonProperty("topSpeed") double topSpeed) {
        super(make, model);
        this.seatingCapacity = seatingCapacity;
        this.topSpeed = topSpeed;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public double getTopSpeed() {
        return topSpeed;
    }
}