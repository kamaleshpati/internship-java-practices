package com.cognitree.stackinarray;

class OverFlowException extends Throwable {

    private final int stackNum;

    public OverFlowException(int stackNum) {
        this.stackNum = stackNum;
    }

    public String toString() {
        return ("Stack" + stackNum + " is Overflowing");
    }
}
