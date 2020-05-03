package com.cognitree.movingaverage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.cognitree.movingaverage.MovingAverage.findAverage;

public class MovingAverageTest {

    @Test
    public void testFindAverage() {
        Integer[] list = new Integer[3];
        CircularList<Integer> circularList = new CircularList<>(list);
        circularList.add(5);
        circularList.add(15);
        circularList.add(25);
        Number average = findAverage(circularList, 3);
        Assertions.assertSame(15, average);
    }
}