package com.cognitree.stackinarray;

class UnderFlowException extends Exception {

    private final int stackNum;

    UnderFlowException(int stackNum) {
        this.stackNum = stackNum;
    }

    public String toString() {
        return ("Stack" + stackNum + " is underflowing");
    }
}
