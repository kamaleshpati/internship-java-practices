package com.cognitree.expressionevaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BalancedParanthesisTest {

    BalancedParanthesis balancedParanthesis = new BalancedParanthesis();

    @Test
    public void testIsMatchingPair() {
        boolean isMatchingPair = balancedParanthesis.isMatchingPair('(', ')');
        Assertions.assertTrue(isMatchingPair);
    }

    @Test
    public void testIsNotMatchingPair() {
        boolean isNotMatchingPair = balancedParanthesis.isMatchingPair('(', '(');
        Assertions.assertFalse(isNotMatchingPair);
        isNotMatchingPair = balancedParanthesis.isMatchingPair(')', ')');
        Assertions.assertFalse(isNotMatchingPair);
    }

    @ParameterizedTest
    @ValueSource(strings = {"()", "()()", "(())"})
    public void testAreParenthesisBalanced(String expr) {
        boolean isBalanced = balancedParanthesis.areParenthesisBalanced(new StringBuffer(expr));
        Assertions.assertTrue(isBalanced);
    }

    @ParameterizedTest
    @ValueSource(strings = {"(", ")()", "(()"})
    public void testAreParenthesisNotBalanced(String expr) {
        boolean isBalanced = balancedParanthesis.areParenthesisBalanced(new StringBuffer(expr));
        Assertions.assertFalse(isBalanced);
    }
}