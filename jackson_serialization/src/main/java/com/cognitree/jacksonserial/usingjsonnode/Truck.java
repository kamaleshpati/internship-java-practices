package com.cognitree.jacksonserial.usingjsonnode;

public class Truck extends Vehicle {

    private double payloadCapacity;

    protected Truck(String make, String model, double payloadCapacity) {
        super(make, model);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }
}