package com.cognitree.jacksonserial.usingclassannotation.usingname;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Vehicle car = new Car("Mercedes-Benz", "S500", 5, 250.0);
        Vehicle truck = new Truck("Isuzu", "NQR", 7500.0);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(truck);
        Fleet fleet1 = new Fleet(vehicles);
        String jsonDataString1 = null;
        try {
            jsonDataString1 = mapper.writeValueAsString(fleet1);
            log.debug("main : {}", jsonDataString1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Fleet fleet = null;
        try {
            fleet = mapper.readValue(jsonDataString1, Fleet.class);
        } catch (IOException e) {
            log.error("main : {}", e.getMessage());
        }
        if (fleet != null) {
            for (Vehicle vehicle : fleet.getL())
                log.debug("main : " + vehicle.getClass() + " make: " + vehicle.getMake() + " model: " + vehicle.getModel());
        }
    }
}

