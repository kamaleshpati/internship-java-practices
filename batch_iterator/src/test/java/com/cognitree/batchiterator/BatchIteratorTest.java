package com.cognitree.batchiterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class BatchIteratorTest {

    private BatchIterator<Number> batchIterator;

    private static ArrayList<Number> generateRandom(int size) {
        ArrayList<Number> randomNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = (int) (Math.random() * ((size) + 1));
            randomNumbers.add(number);
        }
        return randomNumbers;
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    public void testGetBatchWorking(int size) {
        ArrayList<Number> list = generateRandom(100);
        int iteration;
        if (100 % size == 0) {
            iteration = 100 / size;
        } else {
            iteration = 100 / size + 1;
        }
        batchIterator = new BatchIterator<>(list, size);
        for (int i = 0; i < iteration; i++) {
            Assertions.assertEquals(list.get(size * i), batchIterator.next().get(0));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    public void testGetBatchNotWorking(int size) {
        ArrayList<Number> list = generateRandom(50);
        int iteration;
        if (50 % size == 0) {
            iteration = 50 / size;
        } else {
            iteration = 50 / size + 1;
        }
        batchIterator = new BatchIterator<>(list, size);
        for (int i = 0; i < iteration; i++) {
            Assertions.assertNotEquals(101, batchIterator.next().get(0));
        }
    }
}