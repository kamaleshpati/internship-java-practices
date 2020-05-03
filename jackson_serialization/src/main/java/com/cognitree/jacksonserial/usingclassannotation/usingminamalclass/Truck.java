package com.cognitree.jacksonserial.usingclassannotation.usingminamalclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Truck extends Vehicle {

    private double payloadCapacity;

    @JsonCreator
    public Truck(@JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("payloadCapacity") double payloadCapacity) {
        super(make, model);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }
}