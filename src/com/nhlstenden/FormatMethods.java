package com.nhlstenden;

/**
 * This class has a few methods which format the value so it can be used right in the CSV rows
 */
public class FormatMethods {

    /**
     * Returns the CSV field of a String
     * @param value Raw String
     * @return The String which is in right CSV format
     */
    public static String toCsvField(String value) {
        if (value == null) return "";
        boolean noQuotes = true;

        if (value.contains("\"") || value.contains(",")) {
            value = value.replace("\"", "\"\"");
            noQuotes = false;
        }
        return noQuotes ? value : '"' + value + '"';
    }

    /**
     * Converts the integer to the right a value which is good for a row in the CSV file
     * @param value Raw value
     * @return CSV field
     */
    public static String toCsvField(int value) {
        return Integer.toString(value);
    }

    /**
     * Converts the double to the right a value which is good for a row in the CSV file
     * @param value Raw value
     * @return CSV field
     */
    public static String toCsvField(double value) {
        return Double.toString(value);
    }

    /**
     * Converts the year from a matcher to the integer. -1 if the year isn't in the String
     * @param s String to parse
     * @return The year, -1 if year isn't know
     */
    public static int stringToYear(String s) {
        return s.contains("?") ? -1 : Integer.parseInt(s);
    }

}
