package com.pingan.lcloud.common;

import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * @author: xj
 * @version: 2024/8/1
 * @description:
 */
public class Main {
    public static void main(String[] args) {

        try {
            // 请求url
            String urlStr = "http://192.168.1.209:1014/rare/word";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF8");

            // json数据 请求数据
            String jsonData = "{\"id\": \"10\", \"name\": \"杨天翔\", \"yearold\": 18, \"opcode\": \"10140814\"}";
            byte[] dataBytes = jsonData.getBytes("UTF8");

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(dataBytes, 0, dataBytes.length);
            }

            // 响应参数
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), Charset.forName("UTF8")))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
