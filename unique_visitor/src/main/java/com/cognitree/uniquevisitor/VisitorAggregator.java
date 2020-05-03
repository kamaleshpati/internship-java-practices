package com.cognitree.uniquevisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VisitorAggregator {

    private static final Logger log = LoggerFactory.getLogger(VisitorAggregator.class);

    void countUnique(String dataSetPath, int givenTime, char timeFormat) throws ParseException {
        int windowInMillis = getWindowInMillis(givenTime, timeFormat);
        Map<Long, Integer> visitorData = csvVisitorDataFormatter(dataSetPath);
        log.debug("countUnique: size of data = {}", visitorData.size());
        final Map<Long, Set<Integer>> uniqueVisitorsByTime = groupByTimeWindow(visitorData, windowInMillis);
        printOutput(uniqueVisitorsByTime, givenTime, timeFormat);
    }

    private void printOutput(Map<Long, Set<Integer>> uniqueVisitorsByTime, int givenTime, char timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd @ hh-mm-ss");
        List<Long> uniqueWindow = new ArrayList<>(uniqueVisitorsByTime.keySet());
        if (timeFormat == 'h' && givenTime < 24 || timeFormat == 'm') {
            for (Long windowTime : uniqueWindow) {
                Set<Integer> uniqueUsers = uniqueVisitorsByTime.get(windowTime);
                System.out.println(format.format(new Date(windowTime)) + " " + uniqueUsers.size());
            }
        } else if (timeFormat == 'h') {
            for (Long windowTime : uniqueWindow) {
                String[] date = format.format(new Date(windowTime)).split("@");
                Set<Integer> uniqueUsers = uniqueVisitorsByTime.get(windowTime);
                System.out.println(date[0] + " " + uniqueUsers.size());
            }
        }
    }

    private Map<Long, Set<Integer>> groupByTimeWindow(Map<Long, Integer> visitorData, int windowInMillis)
            throws ParseException {
        final Map<Long, Set<Integer>> uniqueVisitorsByTime = new TreeMap<>();
        long start = getStandardTime((long) visitorData.keySet().toArray()[0]);
        log.debug("groupByTimeWindow: start time {}", start);
        Set<Integer> uniqueId = new HashSet<>();
        uniqueVisitorsByTime.put(start, uniqueId);
        for (Long visitTime : visitorData.keySet()) {
            Integer userId = visitorData.get(visitTime);
            Set<Integer> userIds = uniqueVisitorsByTime.get(start);
            if (visitTime >= start + windowInMillis) {
                start = start + windowInMillis;
                uniqueId = new HashSet<>();
                uniqueVisitorsByTime.put(start, uniqueId);
            }
            userIds.add(userId);
        }
        return uniqueVisitorsByTime;
    }

    private long getStandardTime(long lowestTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        String startDate = sdf.format(new Date(lowestTime)) + ":00:00";
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(startDate);
        lowestTime = date.getTime();
        log.debug("getStandardTime: lowest time {}", lowestTime);
        return lowestTime;
    }

    private int getWindowInMillis(int givenTime, char timeFormat) {
        int windowInMillis = 1;
        if (timeFormat == 'm')
            windowInMillis = givenTime * 60000;
        if (timeFormat == 'h')
            windowInMillis = givenTime * 60000 * 60;
        return windowInMillis;
    }

    private Map<Long, Integer> csvVisitorDataFormatter(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(fileName);
        final Map<Long, Integer> visitorData = new TreeMap<>();
        if (stream == null) {
            log.error("csvVisitorDataFormatter: sources name incorrect = {}", fileName);
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                visitorData.put(Long.parseLong(data[0]), Integer.parseInt(data[1]));
            }
            stream.close();
        } catch (IOException ioexception) {
            log.error(ioexception.getMessage());
        }
        return visitorData;
    }
}
