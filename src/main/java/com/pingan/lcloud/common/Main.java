package com.pingan.lcloud.common;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;


/**
 * @author: xj
 * @version: 2024/8/1
 * @description:
 */
public class Main {
    public static void main(String[] args) {

        // 修正URL，移除末尾的单引号
        String url = "http://192.168.1.209:1014/rare/word";
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("charset", "UTF8");

        // 创建请求体
        String dataStr = "{\"id\": \"16\", \"name\": \"杨天翔\", \"yearold\": 18, \"opcode\": \"10140814\"}";
        byte[] dataBytes = dataStr.getBytes(Charset.forName("UTF8"));

        // 创建HttpEntity，包含请求头和请求体
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(dataBytes, headers);

        // 发送POST请求并接收响应
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        // 打印响应文本
        System.out.println(response.getBody());
    }

//        try {
//            // 请求url
//            String urlStr = "http://192.168.1.209:1014/rare/word";
//            URL url = new URL(urlStr);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // 设置headers
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF8");
//
//            // json数据 请求数据
//            String jsonData = "{\"id\": \"10\", \"name\": \"杨天翔\", \"yearold\": 18, \"opcode\": \"10140814\"}";
//            byte[] dataBytes = jsonData.getBytes("UTF8");
//
//            connection.setDoOutput(true);
//            try (OutputStream os = connection.getOutputStream()) {
//                os.write(dataBytes, 0, dataBytes.length);
//            }
//
//            // 响应参数
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), Charset.forName("UTF8")))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println(response.toString());
//            }
//
//            connection.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
