package com.cognitree.windowslider;

import java.util.ArrayList;
import java.util.Iterator;

public class WindowIterator<T> implements Iterator<ArrayList<T>> {

    private final ArrayList<T> batch = new ArrayList<>();
    private final Iterator<T> iterator;
    private final int batchSize;

    WindowIterator(Iterable<T> iterable, int size) {
        this.iterator = iterable.iterator();
        this.batchSize = size;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ArrayList<T> next() {
        if (batch.size() != 0) {
            batch.remove(0);
            if (iterator.hasNext()) {
                batch.add(iterator.next());
            }
            return batch;
        }
        int internalCounter = 0;
        while (iterator.hasNext() && internalCounter < batchSize) {
            batch.add(iterator.next());
            internalCounter++;
        }
        return batch;
    }
}
