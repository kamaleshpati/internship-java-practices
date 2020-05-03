package com.cognitree.movingaverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/*
 *the generic class circular list will take the list
 * and return iterator using iterable interface
 */
public class CircularList<E> implements Iterable<E> {

    private static final Logger log = LoggerFactory.getLogger(MovingAverage.class);

    private final E[] consideredObjects;
    private final int size;
    private int counter;

    CircularList(E[] consideredObjects) {
        this.size = consideredObjects.length;
        this.consideredObjects = consideredObjects; // creating a generic element array
        counter = 0;
    }

    // add the object acCording to the size of circular list by replacing oldest number
    void add(E object) {
        consideredObjects[counter % size] = object;
        counter++;
        log.debug("add: added object {}", object);
    }

    int getCounter() {
        return counter;
    }

    int getSize() {
        return size;
    }

    E[] removeAll() {
       return consideredObjects;
    }

    //using abstract method for interface
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iteratingCounter = 0;   //using  another iterator for getting an elements

            public boolean hasNext() {
                return iteratingCounter < consideredObjects.length;
            }

            public E next() {
                return consideredObjects[iteratingCounter++];
            }
        };
    }
}