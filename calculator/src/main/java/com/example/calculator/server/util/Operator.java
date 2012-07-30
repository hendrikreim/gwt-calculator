package com.example.calculator.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration for operators, helps to identify a given string as operator.
 * Uses a cached hashmap to retrieve the results faster.
 * 
 * Beware: Don't change the order of enumerations for multiplication and division first, then addition and subtraction.
 * 
 * @author hthiess
 */
@SuppressWarnings("javadoc")
public enum Operator {
    DIV("/", "durch", "geteilt durch"),
    MUL("*", "mal", "x"),
    SUB("-", "minus"),
    ADD("+", "plus", "und");

    private static String EXCEPION_MESSAGE = "No valid operator given! Valid operators are: ";

    /**
     * cache for operator aliases
     */
    private static final Map<String, Operator> CACHE;

    static {
        CACHE = new HashMap<String, Operator>();
        for (Operator operator : values()) {
            for (String alias : operator.aliases) {
                CACHE.put(alias, operator);
                EXCEPION_MESSAGE += "\"" + alias + "\", ";
            }
        }
        EXCEPION_MESSAGE = EXCEPION_MESSAGE.substring(0, EXCEPION_MESSAGE.length() - 2) + ".";
    }

    private final String[] aliases;

    private Operator(String... aliases) {
        this.aliases = aliases;
    }

    /**
     * @return the aliases
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Helper method to find a valid operator
     * 
     * @param value
     * @return parsed Operator as Enumeration
     * @throws IllegalArgumentException
     */
    public static Operator parse(String value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException(EXCEPION_MESSAGE);
        }
        value = value.trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(EXCEPION_MESSAGE);
        }
        Operator operator = CACHE.get(value.toLowerCase());
        if (operator == null) {
            throw new IllegalArgumentException(EXCEPION_MESSAGE);
        }
        return operator;
    }
}
