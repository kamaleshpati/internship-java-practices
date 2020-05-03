package com.cognitree.exprvaltoken;

import com.cognitree.exprvaltoken.tokens.Token;
import com.cognitree.exprvaltoken.tokens.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ExpressionParserTest {

    private final ExpressionParser expressionParser = new ExpressionParser("a+b-c");

    @Test
    public void testGetPostfix() {
        ArrayList<Token> postfix = expressionParser.getPostfix();
        Tokenizer tokenizer = new Tokenizer("a+b-c");
        ArrayList<Token> tokens = tokenizer.getTokens();
        Assertions.assertSame(postfix.size(), tokens.size());
    }

    @Test
    public void testGetUniqueVariables() {
        ArrayList<Token> token = new ArrayList<>();
        token.add(new Variable("a"));
        token.add(new Variable("b"));
        int uniqueVariableSize = expressionParser.getUniqueVariables(token).size();
        Assertions.assertSame(2, uniqueVariableSize);
    }
}