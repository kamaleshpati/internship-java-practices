package com.cognitree.exprvaltoken;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

class ExpressionEvaluatorTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static final Logger log = LoggerFactory.getLogger(ExpressionEvaluatorTest.class);

    @Test
    public void testGetVariablesWorking() {
        InputStream stream = loader.getResourceAsStream("getvariable_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(data[0]);
                int variableSize = expressionEvaluator.getVariables().size();
                Assertions.assertEquals(Integer.parseInt(data[1]), variableSize);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
    @Test
    public void testGetVariablesNotWorking() {
        InputStream stream = loader.getResourceAsStream("getvariable_notworking.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(data[0]);
                int variableSize = expressionEvaluator.getVariables().size();
                Assertions.assertNotEquals(Integer.parseInt(data[1]), variableSize);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testEvaluateWorking() {
        InputStream stream = loader.getResourceAsStream("testevaluate_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(data[0]);
                Set<String> variables = expressionEvaluator.getVariables();
                HashMap<String, Number> valueMap=new HashMap<>();
                int counter=0;
                for(String str:variables)
                {
                    valueMap.put(str,Integer.parseInt(data[++counter]));
                }
                Assertions.assertEquals(Float.parseFloat(data[variables.size()+1]),expressionEvaluator.evaluate(valueMap));
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testEvaluateNotWorking() {
        InputStream stream = loader.getResourceAsStream("testevaluate_notworking.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(data[0]);
                Set<String> variables = expressionEvaluator.getVariables();
                HashMap<String, Number> valueMap=new HashMap<>();
                int counter=0;
                for(String str:variables)
                {
                    valueMap.put(str,Integer.parseInt(data[++counter]));
                }
                Assertions.assertNotEquals(Float.parseFloat(data[variables.size()+1]),expressionEvaluator.evaluate(valueMap));
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
}