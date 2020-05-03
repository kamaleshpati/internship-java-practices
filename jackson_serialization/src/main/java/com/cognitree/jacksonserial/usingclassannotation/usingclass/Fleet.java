package com.cognitree.jacksonserial.usingclassannotation.usingclass;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private List<Vehicle> vehicles;

    public Fleet(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Fleet() {
        this.vehicles = new ArrayList();
    }

    public List<Vehicle> getL() {
        return this.vehicles;
    }

    public void setL(List<Vehicle> l) {
        this.vehicles = l;
    }
}
