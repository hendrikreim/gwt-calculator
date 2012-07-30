package com.example.calculator.server.util;

import java.util.List;

/**
 * Calculates the given numbers with the given operators and returns the result.
 * 
 * @author hthiess
 */
public class Calculator {

    /**
     * Calculates the given numbers with the given operators and returns the result.
     * 
     * @param numbers
     * @param operators
     * @return sum
     */
    public static int calculate(List<Integer> numbers, List<Operator> operators) {
        for (Operator operator : Operator.values()) {
            while (operators.contains(operator)) {
                int index = operators.indexOf(operator);
                operators.remove(index);
                int subtotal = calculate(operator, numbers.get(index), numbers.get(index + 1));
                numbers.set(index, subtotal);
                numbers.remove(index + 1);
            }
        }
        return numbers.get(0);
    }

    private static int calculate(Operator operator, int val1, int val2) {
        switch (operator) {
            case ADD:
                return val1 + val2;
            case SUB:
                return val1 - val2;
            case MUL:
                return val1 * val2;
            case DIV:
                return val1 / val2;
            default:
                return 0;
        }
    }
}
