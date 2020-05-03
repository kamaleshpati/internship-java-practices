package com.cognitree.stackinarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;

public class StackInArray<E> {

    private static final Logger log = LoggerFactory.getLogger(StackInArray.class);

    private final E[] stack;
    private final int size;
    private int top1, top2;

    @SuppressWarnings("unchecked")
    StackInArray(Class<E> eClass, int size) {
        stack = (E[]) Array.newInstance(eClass, size);
        this.size = size;
        top1 = -1;
        top2 = size;
    }

    void push(E element, int choice) throws OverFlowException {
        if (top1 < top2 - 1) {
            if (choice == 1) {
                top1++;
                stack[top1] = element;
            } else {
                top2--;
                stack[top2] = element;
            }
        } else {
            throw new OverFlowException(choice);

        }
    }

    E pop(int choice) throws UnderFlowException {
        E element = null;
        if ((top1 >= 0) || (top2 < size)) {
            switch (choice) {
                case 1:
                    if (top1 >= 0) {
                        element = stack[top1--];
                    }
                    break;
                case 2:
                    if (top2 < size) {
                        element = stack[top2++];
                    }
                    break;
            }
        } else {
            throw new UnderFlowException(choice);
        }
        return element;
    }

    E peek(int choice) throws UnderFlowException {
        E element = null;
        switch (choice) {
            case 1:
                if (top1 == -1) {
                    throw new UnderFlowException(choice);
                } else
                    element = stack[top1];
                break;
            case 2:
                if (top2 == 10) {
                    throw new UnderFlowException(choice);
                } else
                    element = stack[top2];
                break;
        }
        return element;
    }

    void display(int choice) {
        if (choice == 1) {
            log.debug("1st stack");
            for (int i = 0; i <= top1; i++)
                log.debug(" {} ", stack[i]);
        } else {
            log.debug("2nd stack");
            for (int i = 0; i < size - top2; i++)
                log.debug(" {} ", stack[size - i - 1]);
        }
    }
}
