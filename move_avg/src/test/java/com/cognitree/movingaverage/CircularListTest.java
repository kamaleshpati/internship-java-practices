package com.cognitree.movingaverage;

import org.junit.jupiter.api.*;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CircularListTest {

    private final Integer[] list = new Integer[3];
    private final CircularList<Integer> circularList = new CircularList<>(list);

    @BeforeAll
    public void addTest() {
        circularList.add(5);
        circularList.add(15);
        circularList.add(25);
    }

    @Test
    public void testPostCreate() {
        Assertions.assertSame(3, circularList.getCounter());
        Assertions.assertSame(3, circularList.getSize());
    }

    @AfterAll
    public void testRemoveAll() {
        Arrays.fill(circularList.removeAll(), null);
    }
}