package com.example.calculator.server.util;

import static com.example.calculator.server.util.Calculator.calculate;
import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author hthiess
 * 
 */
public class CalculatorTest {
    private final List<Integer> numbers = new ArrayList<Integer>();
    private final List<Operator> operators = new ArrayList<Operator>();

    /**
     * 
     */
    @Before
    public void setUp() {
        operators.clear();
        numbers.clear();
        numbers.add(100);
        numbers.add(2);
        numbers.add(5);
        numbers.add(5);
        numbers.add(2);
    }

    /**
     * 
     */
    @Test
    public void testAddition() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            operators.add(Operator.ADD);
        }
        assertEquals(114, calculate(numbers, operators));
    }

    /**
     * 
     */
    @Test
    public void testSubstraction() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            operators.add(Operator.SUB);
        }
        assertEquals(86, calculate(numbers, operators));
    }

    /**
     * 
     */
    @Test
    public void testMultiplication() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            operators.add(Operator.MUL);
        }
        assertEquals(10000, calculate(numbers, operators));
    }

    /**
     * 
     */
    @Test
    public void testDivision() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            operators.add(Operator.DIV);
        }
        assertEquals(1, calculate(numbers, operators));
    }

    /**
     * 
     */
    @Test
    public void testAllOperators() {
        // 100 / 2 * 5 + 5 - 2 = 253
        operators.add(Operator.DIV);
        operators.add(Operator.MUL);
        operators.add(Operator.ADD);
        operators.add(Operator.SUB);
        assertEquals(253, calculate(numbers, operators));
    }

    /**
     * 
     */
    @Test
    public void testOperatorPrecedence() {
        // 100 + 2 * 5 / 5 - 2
        operators.add(Operator.ADD);
        operators.add(Operator.MUL);
        operators.add(Operator.DIV);
        operators.add(Operator.SUB);
        assertEquals(100, calculate(numbers, operators));
    }
}
