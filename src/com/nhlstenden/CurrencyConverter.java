package com.nhlstenden;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private final JSONObject exchangeRates;

    public CurrencyConverter() throws IOException {
        String exchangeRatesStr = getExchangeRates();
        this.exchangeRates = new JSONObject(exchangeRatesStr).getJSONObject("conversion_rates");
    }

    public double convertToEur(double amount, String currency) {
        try {
            return amount / exchangeRates.getDouble(currency);
        } catch (JSONException ex) {
            return -1;
        }
    }

    public double convertToEur(String amount, String currency) {

        StringBuilder stringBuilder = new StringBuilder(amount);
        int indexOfComma = stringBuilder.indexOf(",");
        while (indexOfComma != -1) {
            stringBuilder.deleteCharAt(indexOfComma);
            indexOfComma = stringBuilder.indexOf(",");
        }

        return convertToEur(Double.parseDouble(String.valueOf(stringBuilder)), currency);
    }

    private String getExchangeRates() throws IOException {
        String apiUrl = "https://v6.exchangerate-api.com/v6/76b97d9b3e2b86fc32149119/latest/EUR";
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        InputStream inputStream = connection.getInputStream();

        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
