package com.cognitree.dataextractionsmsregex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArrayList<TransactionData> datas = new CitiBankSmsParser().getData("sampleciti.txt");
        for (TransactionData data : datas) {
            log.debug("main :{}",data.getData());
        }
    }

}
