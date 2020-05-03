package com.cognitree.stackinarray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackInArrayTest {

    @Test
    public void testPushPopWorking() throws OverFlowException, UnderFlowException {
        int[] array = {10, 20, 30, 40};
        StackInArray<Integer> stack = new StackInArray<>(Integer.class, array.length);
        for (int value : array) {
            stack.push(value, 1);
            Assertions.assertEquals(value, stack.peek(1));
        }
        Assertions.assertThrows(OverFlowException.class, () -> {
            stack.push(99, 1);
        });
        for (int i = array.length - 1; i >= 0; i--) {
            int element = stack.peek(1);
            stack.pop(1);
            Assertions.assertEquals(array[i], element);
        }
        Assertions.assertThrows(UnderFlowException.class, () -> {
            stack.pop(1);
        });
        for (int value : array) {
            stack.push(value, 2);
            Assertions.assertEquals(value, stack.peek(2));
        }
        Assertions.assertThrows(OverFlowException.class, () -> {
            stack.push(99, 2);
        });
        for (int i = array.length - 1; i >= 0; i--) {
            int element = stack.peek(2);
            stack.pop(2);
            Assertions.assertEquals(array[i], element);
        }
        Assertions.assertThrows(UnderFlowException.class, () -> {
            stack.pop(2);
        });

    }

    @Test
    public void testPushPopNotWorking() throws UnderFlowException, OverFlowException {
        int[] array = {10, 20, 30, 40};
        int[] errArray = {20, 30, 40, 50};
        StackInArray<Integer> stack = new StackInArray<>(Integer.class, array.length);
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i], 1);
            Assertions.assertNotEquals(errArray[i], stack.peek(1));
        }
        OverFlowException overFlowException = Assertions.assertThrows(OverFlowException.class, () -> stack.push(100, 1));
        Assertions.assertNotEquals("stack1 is underflowing", overFlowException.toString());
        for (int i = array.length - 1; i >= 0; i--) {
            int element = stack.peek(1);
            stack.pop(1);
            Assertions.assertNotEquals(errArray[i], element);
        }
        UnderFlowException underFlowException = Assertions.assertThrows(UnderFlowException.class, () -> {
            stack.pop(1);
        });
        Assertions.assertNotEquals("stack1 is overflowing", underFlowException.toString());
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i], 2);
            Assertions.assertNotEquals(errArray[i], stack.peek(2));
        }
        overFlowException = Assertions.assertThrows(OverFlowException.class, () -> stack.push(100, 2));
        Assertions.assertNotEquals("stack2 is underflowing", overFlowException.toString());

        for (int i = array.length - 1; i >= 0; i--) {
            int element = stack.peek(2);
            stack.pop(2);
            Assertions.assertNotEquals(errArray[i], element);
        }
        underFlowException = Assertions.assertThrows(UnderFlowException.class, () -> {
            stack.pop(2);
        });
        Assertions.assertNotEquals("stack2 is overflowing", underFlowException.toString());
    }
}