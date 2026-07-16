package com.mycompany.project;

import java.net.HttpURLConnection;
import java.net.URL;

public class SMSSender {

    public static void sendSMS(String phone, String message) {
    try {
        String apiKey = "t4smqrHfDxbvlIWESiN8ZpLQ6uOTce97FRYyo3CkVP5GwzMXndKntrhzH594DQLvJcgUdM2VGNm3CbyT";

        String urlStr = "https://www.fast2sms.com/dev/bulk?" +
                "authorization=" + apiKey +
                "&sender_id=FSTSMS" +
                "&message=" + java.net.URLEncoder.encode(message, "UTF-8") +
                "&language=english" +
                "&route=p" +   // ✅ important
                "&numbers=" + phone;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        System.out.println("SMS Response Code: " + responseCode);

        java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(conn.getInputStream())
        );

        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        System.out.println("SMS Response: " + response.toString());

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}