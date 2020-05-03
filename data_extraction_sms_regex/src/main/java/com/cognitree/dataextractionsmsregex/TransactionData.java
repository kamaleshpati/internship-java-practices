package com.cognitree.dataextractionsmsregex;

import java.util.ArrayList;

public class TransactionData {

    private String amount;
    private String total;
    private String vendorParty;
    private String cardNo;
    private String date;
    private String type;

    TransactionData(String amount, String total, String vendorParty, String card, String date, String type) {
        this.amount = amount;
        this.total = total;
        this.vendorParty = vendorParty;
        this.cardNo = card;
        this.date = date;
        this.type = type;
    }

    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(this.type);
        data.add(this.amount);
        data.add(this.total);
        data.add(this.vendorParty);
        data.add(this.cardNo);
        data.add(this.date);
        return data;
    }
}
