package com.cognitree.yamljsonparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        Map<String, Object> obj = new FileParser().yamlToMap("customer.yaml");
        log.debug("main : {}",obj);
        Map<String, Object> obj1 = new FileParser().yamlToMap("customer.json");
        log.debug("main : {}",obj1);
        new FileParser().jsonToString("customer.json");
        Map<String, Object> obj2=new FileParser().jsonToMap("customer.json");
        log.debug("main : {}",obj2);
        Map<String, Object> obj3=new FileParser().jsonToMap("customer.yaml");
        log.debug("main : {}",obj3);
    }
}