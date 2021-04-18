package com.nhlstenden;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * This class converts the different currencies to EUR
 */
public class CurrencyConverter {

    private final JSONObject exchangeRates;

    /**
     * Get the exchange rates from the API
     * @throws IOException There is no internet!
     */
    public CurrencyConverter() throws IOException {
        String exchangeRatesStr = getExchangeRates();
        this.exchangeRates = new JSONObject(exchangeRatesStr).getJSONObject("conversion_rates");
    }

    /**
     * Try to find exchange rate in the exchangeRates object.
     * And returns the value in EUR
     * @param amount The amount in the other currency
     * @param currency The currency to convert from
     * @return The converted value
     */
    public double convertToEur(double amount, String currency) {
        try {
            return amount / exchangeRates.getDouble(currency);
        } catch (JSONException ex) {
            return -1;
        }
    }

    /**
     * Try to find exchange rate in the exchangeRates object.
     * And return the value in EUR
     * @param amount The amount in the other currency
     * @param currency The currency to convert from
     * @return The converted value
     */
    public double convertToEur(String amount, String currency) {
        // We'll remove all the comma's because otherwise we can't convert it to an integer!
        StringBuilder stringBuilder = new StringBuilder(amount);
        int indexOfComma = stringBuilder.indexOf(",");
        while (indexOfComma != -1) {
            stringBuilder.deleteCharAt(indexOfComma);
            indexOfComma = stringBuilder.indexOf(",");
        }

        return convertToEur(Double.parseDouble(String.valueOf(stringBuilder)), currency);
    }

    /**
     * Gets the exchangeRates from the API
     * @return The response of the API in a String
     * @throws IOException There was a network error
     */
    private String getExchangeRates() throws IOException {
        String apiUrl = "https://v6.exchangerate-api.com/v6/76b97d9b3e2b86fc32149119/latest/EUR";
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        InputStream inputStream = connection.getInputStream();

        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
