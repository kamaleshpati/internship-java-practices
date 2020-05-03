package com.cognitree.batchiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BatchIterator<T> implements Iterator<List<T>> {

    private final Iterator<T> iterator;
    private final int batchSize;

    BatchIterator(Iterable<T> iterable, int size) {
        this.iterator = iterable.iterator();
        this.batchSize = size;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<T> next() {
        ArrayList<T> batch = new ArrayList<>();
        int internalCounter = 0;
        while (iterator.hasNext() && internalCounter < batchSize) {
            batch.add(iterator.next());
            internalCounter++;
        }
        while (internalCounter < batchSize) {
            batch.add(null);
            internalCounter++;
        }
        return batch;
    }
}
