package com.dsr.javaschool.langtrainer.utils;


import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import static java.lang.String.format;

public class YandexTranslator {

    private static final String api_key = "trnsl.1.1.20161210T230614Z.8ec47badee37170c.1fc6f00b8430e698f0ccfb107f8555f784f608f1";
    private static final String path = format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s", api_key);

    public static String translate(String lang, String word) throws IOException {
        URL url = new URL(path);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(word, "UTF-8") + "&lang=" + lang);
        InputStream response = connection.getInputStream();
        String json = new Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        return json.substring(start + 2, end - 1).toLowerCase();
    }

}
