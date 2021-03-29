package com.nhlstenden;

import java.util.regex.Matcher;

public class FormatMethods {

    public static String toCsvField(String value) {
        if (value == null) return "";
        boolean noQuotes = true;

        if (value.contains("\"") || value.contains(",")) {
            value = value.replace("\"", "\"\"");
            noQuotes = false;
        }
        return noQuotes ? value : '"' + value + '"';
    }

    public static String toCsvField(int value) {
        return Integer.toString(value);
    }

    public static String toCsvField(double value) {
        return Double.toString(value);
    }

    public static int stringToYear(String s) {
        return s.contains("?") ? -1 : Integer.parseInt(s);
    }

}
