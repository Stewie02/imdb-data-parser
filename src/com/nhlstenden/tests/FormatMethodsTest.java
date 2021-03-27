package com.nhlstenden.tests;

import com.nhlstenden.FormatMethods;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FormatMethodsTest {

    @Test
    public void simpleTest() {
        String value = "hallo";
        String field = FormatMethods.toCsvField(value);
        String expected = "hallo";
        System.out.println("Simple: " + field);
        assertEquals(field, expected);
    }

    @Test
    public void withQuote() {
        String value = "hal\"lo";
        String field = FormatMethods.toCsvField(value);
        String expected = "\"hal\"\"lo\"";
        System.out.println("Quote: " + field);
        assertEquals(field, expected);
    }

    @Test
    public void withComma() {
        String value = "hal,lo";
        String field = FormatMethods.toCsvField(value);
        String expected = "\"hal,lo\"";
        System.out.println("Comma: " + field);
        assertEquals(field, expected);
    }

    @Test
    public void withCommaAndQuote() {
        String value = "hal,\"lo";
        String field = FormatMethods.toCsvField(value);
        String expected = "\"hal,\"\"lo\"";
        System.out.println("CommaQuote: " + field);
        assertEquals(field, expected);
    }
}