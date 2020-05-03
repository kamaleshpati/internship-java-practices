package com.cognitree.windowslider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class WindowIteratorTest {

    private WindowIterator<Number> windows;

    private ArrayList<Number> generateRandom() {
        ArrayList<Number> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int number = (int) (Math.random() * (101));
            arrayList.add(number);
        }
        return arrayList;
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    public void testGetWindowWorking(int size) {
        ArrayList<Number> list = generateRandom();
        windows = new WindowIterator<>(list, size);
        for (int i = 0; i < list.size() - size + 1; i++) {
            Assertions.assertEquals(list.get(i), windows.next().get(0));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    public void testGetWindowNotWorking(int size) {
        ArrayList<Number> list = generateRandom();
        windows = new WindowIterator<>(list, size);
        for (int i = 0; i < list.size() - size + 1; i++) {
            Assertions.assertNotEquals(101, windows.next().get(0));
        }
    }
}