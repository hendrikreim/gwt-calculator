package com.example.calculator.server.util;

import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TermParser parses an integer or given german numeral.
 * Uses Number and Unit enumerations as helper.
 * 
 * @author hthiess
 */
public class TermParser {
    private static final Logger LOG = LoggerFactory.getLogger(TermParser.class);

    private static final Set<Entry<String, IsFraction>> UNIT_ENTRIES = Unit.CACHE.entrySet();
    private static final Set<Entry<String, IsFraction>> NUMBER_ENTRIES = Number.CACHE.entrySet();
    private static final String AND = "und";

    /**
     * this method tries to parse a term by following steps:
     * 
     * <pre>
     * 1) get the sign for the value
     * 2) try to parse value as Integer
     * 3) try to parse value as self defined number (0 - 12)
     * 4) try to parse value as fully german numeral
     * </pre>
     * 
     * @param value
     * @return the parsed term as int
     */
    public static int parse(String value) {
        if (value == null) {
            throw new IllegalArgumentException("No valid number given.");
        }
        value = value.trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("No valid number given.");
        }
        // get the sign
        int sign = 1;
        for (String alias : Operator.SUB.getAliases()) {
            if (value.startsWith(alias)) {
                value = value.substring(alias.length());
                sign = -1;
            }
        }
        // try to parse value as Integer
        try {
            return Integer.parseInt(value) * sign;
        } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Integer expected, " + value + " found.");
            }
        }
        // try to parse value as self defined number from zero to twelve
        try {
            return Number.parse(value) * sign;
        } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Number expected, " + value + " found.");
            }
        }
        // parsing german numeral
        StringBuilder builder = new StringBuilder(value);
        int number = 0;
        while (!(value = builder.toString()).isEmpty()) {
            if (value.startsWith(AND)) {
                builder.delete(0, AND.length());
            }
            Integer numeralResult = parseFraction(builder, NUMBER_ENTRIES);
            // special case #1: no numeral found, remove first char and continue while
            if (numeralResult == null) {
                builder.delete(0, 1);
                continue;
            }
            // special case #2: e.g. for neunundneunzigtausend
            Integer secondNumeralResult = null;
            if (builder.toString().startsWith(AND)) {
                builder.delete(0, AND.length());
                secondNumeralResult = parseFraction(builder, NUMBER_ENTRIES);
                if (secondNumeralResult != null) {
                    numeralResult += secondNumeralResult * Unit.TEN.getNumber();
                }
            }
            Integer unitResult = parseFraction(builder, UNIT_ENTRIES);
            if (unitResult == null) {
                unitResult = 1;
            }
            // special case #3: if hundred was found, but ten thousand follows
            if (unitResult == Unit.HUNDRED.getNumber()) {
                value = builder.toString();
                for (String alias : Unit.TENTHOUSAND.getAliases()) {
                    if (value.contains(alias)) {
                        // multiply hundred by thousand to get hundred thousand
                        unitResult *= Unit.THOUSAND.getNumber();
                        break;
                    }
                }
            }
            // end of special case #2: divide by 10, if a second numeral was found
            if (secondNumeralResult != null && unitResult > 1) {
                unitResult /= Unit.TEN.getNumber();
            }
            number += numeralResult * unitResult;
        }
        // normally, the number shouldn't be zero here.
        // we tried to parse by Number.parse(value) before, which contains zero.
        if (number == 0) {
            throw new IllegalArgumentException("Number expected, " + value + " found.");
        }
        return number * sign;
    }

    /**
     * Helper method for parsing of a fraction
     * 
     * @param builder
     * @param pool
     * @return an integer or null if no fraction was found
     */
    private static Integer parseFraction(StringBuilder builder, Set<Entry<String, IsFraction>> pool) {
        String value = builder.toString();
        Entry<String, IsFraction> foundEntry = null;
        for (Entry<String, IsFraction> entry : pool) {
            String key = entry.getKey();
            if (value.startsWith(key)) {
                if (foundEntry == null || foundEntry.getValue().getNumber() < entry.getValue().getNumber()) {
                    foundEntry = entry;
                }
            }
        }
        if (foundEntry == null) {
            return null;
        }
        builder.delete(0, foundEntry.getKey().length());
        return foundEntry.getValue().getNumber();
    }
}
