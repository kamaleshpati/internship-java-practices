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
import java.util.stream.IntStream;

public class IciciSmsParser {

    private static final Logger log = LoggerFactory.getLogger(IciciSmsParser.class);

    private final String debitedSmsRegEx = "Txn of INR ([\\d{1,3}]+\\.\\d{2}) done on Acct (\\w*) on (\\d{2}-\\w{3}-\\d{2,4})\\.Info:(.*) \\.Avbl Bal:INR ([\\d{1,3}]+\\.\\d{2})\\.";

    private final ArrayList<ArrayList<String>> txnDatas;

    IciciSmsParser() {
        txnDatas = new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> getData(String fileName) {
        ArrayList<String> lines = getLines(fileName);
        Pattern pattern = Pattern.compile(debitedSmsRegEx);
        lines.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> IntStream.range(0, 5)
                        .mapToObj(j -> matcher.group(j + 1))
                        .collect(Collectors.toCollection(ArrayList::new)))
                .forEachOrdered(txnDatas::add);
        return this.txnDatas;
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