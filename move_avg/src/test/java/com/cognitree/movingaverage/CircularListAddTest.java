package com.cognitree.movingaverage;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Iterator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CircularListAddTest {

    private final Integer[] list = new Integer[3];
    private final CircularList<Integer> circularList = new CircularList<>(list);

    @BeforeAll
    public void addTest() {
        circularList.add(5);
        circularList.add(15);
        circularList.add(25);
    }

    @Test
    public void testAdd() {
        Assertions.assertSame(3, circularList.getCounter());
        Assertions.assertSame(3, circularList.getSize());
        circularList.add(4);
        Assertions.assertSame(3, circularList.getCounter());
        Assertions.assertSame(3, circularList.getSize());
        Iterator<Integer> iter = circularList.iterator();
        Assertions.assertEquals(15, iter.next());
        Assertions.assertEquals(25, iter.next());
        Assertions.assertEquals(4, iter.next());
        Assertions.assertFalse(iter.hasNext());
    }

    @AfterAll
    public void testRemoveAll() {
        Arrays.fill(circularList.removeAll(), null);
    }
}