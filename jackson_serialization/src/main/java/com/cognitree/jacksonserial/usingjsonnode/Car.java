package com.cognitree.jacksonserial.usingjsonnode;

public class Car extends Vehicle {

    private int seatingCapacity;
    private double topSpeed;

    protected Car(String make, String model, int seatingCapacity, double topSpeed) {
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