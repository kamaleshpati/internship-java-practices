package com.cognitree.expressionevaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class EvaluationOfExpressionTest {

    private final EvaluationOfExpression evaluationOfExpression = new EvaluationOfExpression();
    private ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static final Logger log = LoggerFactory.getLogger(EvaluationOfExpressionTest.class);

    @Test
    public void testEvaluatePostfixWorking() {
        InputStream stream = loader.getResourceAsStream("eval_postfix_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Assertions.assertEquals(Integer.parseInt(data[1]), evaluationOfExpression.evaluatePostfix(data[0]));
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testEvaluatePostfixNotWorking() {
        InputStream stream = loader.getResourceAsStream("eval_postfix_not_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Assertions.assertNotEquals(evaluationOfExpression.evaluatePostfix(data[0]), Integer.parseInt(data[1]));
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
}