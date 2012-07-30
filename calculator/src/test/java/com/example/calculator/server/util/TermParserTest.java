package com.example.calculator.server.util;

import static com.example.calculator.server.util.TermParser.parse;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

/**
 * @author hthiess
 * 
 */
public class TermParserTest {

    /**
     * 
     */
    @Test
    public void testParseInteger() {
        assertEquals(0, parse("0"));
        assertEquals(1, parse("1"));
        assertEquals(2, parse("2"));
        assertEquals(3, parse("3"));
        assertEquals(4, parse("4"));
        assertEquals(5, parse("5"));
        assertEquals(6, parse("6"));
        assertEquals(7, parse("7"));
        assertEquals(8, parse("8"));
        assertEquals(9, parse("9"));
        assertEquals(10, parse("10"));
        assertEquals(11, parse("11"));
        assertEquals(12, parse("12"));
        assertEquals(20, parse("20"));
        assertEquals(30, parse("30"));
    }

    /**
     * 
     */
    @Test
    public void testParseSimpleNumbers() {
        assertEquals(0, parse("null"));
        assertEquals(1, parse("eins"));
        assertEquals(2, parse("zwei"));
        assertEquals(3, parse("drei"));
        assertEquals(4, parse("vier"));
        assertEquals(5, parse("fünf"));
        assertEquals(6, parse("sechs"));
        assertEquals(7, parse("sieben"));
        assertEquals(8, parse("acht"));
        assertEquals(9, parse("neun"));
        assertEquals(10, parse("zehn"));
        assertEquals(11, parse("elf"));
        assertEquals(12, parse("zwölf"));
    }

    /**
     * 
     */
    @Test
    public void testMinNumbers() {
        assertEquals(-9000000, parse("minusneunmillionen"));
        assertEquals(-9900000, parse("minusneunmillionenneunhunderttausend"));
        assertEquals(-9990000, parse("minusneunmillionenneunhundertneunzigtausend"));
        assertEquals(-9999000, parse("minusneunmillionenneunhundertneunundneunzigtausend"));
        assertEquals(-9999900, parse("minusneunmillionenneunhundertneunundneunzigtausendneunhundert"));
        assertEquals(-9999990, parse("minusneunmillionenneunhundertneunundneunzigtausendneunhundertneunzig"));
        assertEquals(-9999999, parse("minusneunmillionenneunhundertneunundneunzigtausendneunhundertneunundneunzig"));
    }

    /**
     * 
     */
    @Test
    public void testMaxNumbers() {
        assertEquals(9000000, parse("neunmillionen"));
        assertEquals(9900000, parse("neunmillionenneunhunderttausend"));
        assertEquals(9990000, parse("neunmillionenneunhundertneunzigtausend"));
        assertEquals(9999000, parse("neunmillionenneunhundertneunundneunzigtausend"));
        assertEquals(9999900, parse("neunmillionenneunhundertneunundneunzigtausendneunhundert"));
        assertEquals(9999990, parse("neunmillionenneunhundertneunundneunzigtausendneunhundertneunzig"));
        assertEquals(9999999, parse("neunmillionenneunhundertneunundneunzigtausendneunhundertneunundneunzig"));
    }

    /**
     * 
     */
    @Test
    public void testRandomNumbers() {
        assertEquals(13, parse("dreizehn"));
        assertEquals(21, parse("einundzwanzig"));
        assertEquals(40, parse("vierzig"));
        assertEquals(41, parse("einundvierzig"));
        assertEquals(99, parse("neunundneunzig"));
        assertEquals(392, parse("dreihundertzweiundneunzig"));
        assertEquals(7890, parse("siebentausendachthundertundneunzig"));
        assertEquals(52380, parse("zweiundfünfzigtausenddreihundertachtzig"));
        assertEquals(800000, parse("achthunderttausend"));
    }

    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWrongString() {
        parse("foobar");
    }

    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        parse(null);
    }

    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        parse("");
    }

    /**
     * 
     */
    public void testNumberWithWrongString() {
        assertEquals(30, parse("dreißigbla"));
    }
}
