package com.cognitree.dataextractionsmsregex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CitiBankSmsParser {

    private static final Logger log = LoggerFactory.getLogger(CitiBankSmsParser.class);

    private static final String REMINDERMSGREGEX = "Reminder: Payment for card(.+) is due on (\\d{2}-\\w{3}-\\d{2,4})\\. Total=Rs\\.([\\d{1,3}]+\\.\\d{2}),Minimum=Rs.([\\d{1,3}]+\\.\\d{2})\\.";

    private static final String VENDORCREDITMSGREGEX = "Credit of Rs\\.([\\d{1,3}]+\\.\\d{2}) received from (.+) has been processed on your Credit Card (\\**\\d*) on (\\d{2}/\\d{2}/\\d{2,4})";

    private static final String PAYMENTCREDITMSGREGEX = "Payment of Rs\\. ([\\d{1,3}]+\\.\\d{2}) received for Card No (\\w+) on (\\d{2}-\\w{3}-\\d{2,4})\\. Available credit limit is Rs. ([\\d{1,3}]+\\.\\d{2})\\.";

    private static final String PAYMENTDEBITMSGREGEX = "Rs\\. ([\\d{1,3}]+\\.\\d{2}) spent on card (\\w{3,16}) on (\\d{2}-\\w{3}-\\d{2,4}) at (.*). Limit available=Rs. ([\\d{1,3}]+\\.\\d{2})\\.";

    private final ArrayList<TransactionData> txnDatas;
    private final Pattern[] patterns;

    CitiBankSmsParser() {
        txnDatas = new ArrayList<>();
        patterns = new Pattern[4];
        patterns[0] = Pattern.compile(REMINDERMSGREGEX);
        patterns[1] = Pattern.compile(PAYMENTCREDITMSGREGEX);
        patterns[2] = Pattern.compile(VENDORCREDITMSGREGEX);
        patterns[3] = Pattern.compile(PAYMENTDEBITMSGREGEX);
    }

    public ArrayList<TransactionData> getData(String fileName) {
        ArrayList<String> lines = getLines(fileName);
        Matcher matcher;
        for (String line : lines) {
            for (int i = 0; i < patterns.length; i++) {
                matcher = patterns[i].matcher(line);
                if (matcher.find()) {
                    setTransactionData(matcher, i);
                    break;
                }
            }
        }
        return this.txnDatas;
    }

    //String amount, String total, String vendorParty, String card, String date, String type
    private void setTransactionData(Matcher matcher, int choice) {
        switch (choice) {
            case 0:
                txnDatas.add(new TransactionData(matcher.group(3), "", "", matcher.group(1), matcher.group(2), "reminder"));
                break;
            case 1:
                txnDatas.add(new TransactionData(matcher.group(1), matcher.group(4), matcher.group(2), "", matcher.group(3), "credit"));
                break;
            case 2:
                txnDatas.add(new TransactionData(matcher.group(1), "", matcher.group(2), matcher.group(3), matcher.group(4), "credit"));
                break;
            case 3:
                txnDatas.add(new TransactionData(matcher.group(1), matcher.group(5), matcher.group(4), matcher.group(2), matcher.group(3), "debit"));
                break;
            default:
                break;
        }
    }

    private ArrayList<String> getLines(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ArrayList<String> lines = new ArrayList<>();
        try (InputStream stream = loader.getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader
                     (new InputStreamReader(Objects.requireNonNull(stream)))) {
            lines = (ArrayList<String>) bufferedReader.lines()
                    .collect(Collectors
                            .toList());
        } catch (IOException ioexception) {
            log.error("getLines : sources name incorrect = {}", fileName);
        }
        return lines;
    }
}