package com.example.calculator.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hthiess
 * 
 */
@SuppressWarnings("javadoc")
public enum Unit implements IsFraction {
    MILLION(1000000, "millionen", "million"),
    HUNDREDTHOUSAND(100000, "hunderttausend"),
    TENTHOUSAND(10000, "zigtausend", "ßigtausend", "ssigtausend"),
    THOUSAND(1000, "tausend"),
    HUNDRED(100, "hundert"),
    TEN(10, "zig", "ßig", "ssig");

    public static final Map<String, IsFraction> CACHE;

    static {
        CACHE = new HashMap<String, IsFraction>();
        for (Unit numeral : values()) {
            for (String alias : numeral.aliases) {
                CACHE.put(alias, numeral);
            }
        }
    }

    private final int number;
    private final String[] aliases;

    private Unit(int unit, String... aliases) {
        this.number = unit;
        this.aliases = aliases;
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
