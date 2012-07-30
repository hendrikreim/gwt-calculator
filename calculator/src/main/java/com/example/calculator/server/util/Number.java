package com.example.calculator.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hthiess
 * 
 */
@SuppressWarnings("javadoc")
public enum Number implements IsFraction {
    ZERO(0, "null"),
    ONE(1, "ein", "eins"),
    TWO(2, "zwei", "zwan"),
    THREE(3, "drei"),
    FOUR(4, "vier"),
    FIVE(5, "fünf", "fuenf"),
    SIX(6, "sechs", "sech"),
    SEVEN(7, "sieben"),
    EIGHT(8, "acht"),
    NINE(9, "neun"),
    TEN(10, "zehn"),
    ELEVEN(11, "elf"),
    TWELVE(12, "zwölf", "zwoelf");

    public static final Map<String, IsFraction> CACHE;

    static {
        CACHE = new HashMap<String, IsFraction>();
        for (Number number : values()) {
            for (String alias : number.aliases) {
                CACHE.put(alias, number);
            }
        }
    }

    private final int number;
    private final String[] aliases;

    private Number(int number, String... aliases) {
        this.number = number;
        this.aliases = aliases;
    }

    /**
     * Helper method to find a german numeral between 0 and 12
     * 
     * @param value
     * @return integer between 0 and 12
     * @throws IllegalArgumentException
     */
    public static int parse(String value) throws IllegalArgumentException {
        IsFraction number = CACHE.get(value.toLowerCase());
        if (number == null) {
            throw new IllegalArgumentException("No enum const " + Number.class + "@description." + value);
        }
        return number.getNumber();
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }
}