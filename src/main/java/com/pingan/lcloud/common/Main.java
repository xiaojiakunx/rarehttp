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
            // 修正URL，移除末尾的单引号
            String urlStr = "http://192.168.1.209:1014/rare/word";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法和头信息
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=GBK");

            // 创建请求体
            String jsonData = "{\"id\": \"8\", \"name\": \"杨天翔\", \"yearold\": 18, \"opcode\": \"10140814\"}";
            byte[] dataBytes = jsonData.getBytes("GBK");

            // 发送请求
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(dataBytes, 0, dataBytes.length);
            }

            // 读取响应
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), Charset.forName("GBK")))) {
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


//        String url = "http://192.168.1.209:1014/rare/word";
//        String dataStr = "{\"id\": \"3\", \"name\": \"杨天翔\", \"yearold\": 18, \"opcode\": \"10140814\"}";
//        byte[] dataBytes = dataStr.getBytes(StandardCharsets.ISO_8859_1); // 使用ISO-8859-1编码，因为GBK在Java中不直接支持
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", "application/json; charset=ISO-8859-1") // 由于Java中不直接支持GBK，这里使用ISO-8859-1代替
//                .POST(HttpRequest.BodyPublishers.ofByteArray(dataBytes))
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//            printHi("PyCharm");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String dataStr = "示例文本数据";    // 数据内容
//        String targetURL = "http://example.com"; // 目标URL
//
//        // 使用GBK进行编码
//        byte[] data  = dataStr.getBytes(StandardCharsets.UTF_8);
//
//        HttpURLConnection connection = null;
//
//        try {
//            URL url = new URL(targetURL);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true); // 允许输入流
//            connection.setDoOutput(true); // 允许输出流
//            connection.setUseCaches(false); // 不允许缓存
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            connection.setRequestProperty("Charset", "GBK");
//            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//            // 创建输出流
//            OutputStream outStream = connection.getOutputStream();
//            DataOutputStream dos = new DataOutputStream(outStream);
//            dos.write(data);   // 写入请求的字符串
//            dos.flush();
//            dos.close();
//            outStream.close();
//
//            // 获取响应码
//            int responseCode = connection.getResponseCode();
//            String responseMessage = connection.getResponseMessage();
//            System.out.println("HTTP Response Code : " + responseCode + ", HTTP Response Message : " + responseMessage);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//
//    }


//        System.out.println("你好! 杨天翔");
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://192.168.1.209:1014/controller"; // 后端控制器的URL
//        String data = "肖佳"; // 需要发送的字符串
//        System.out.println("肖佳");
//
//        try {
//            // 使用URLEncoder编码GBK字符串
//            String encodedData = URLEncoder.encode(data, "GBK");
//            System.out.println(data);
//
//            // 创建请求体
//            String requestBody = "data=" + encodedData;
//
//            // 创建HttpHeaders并设置字符集为GBK
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(new MediaType("application","x-www-form-urlencoded", Charset.forName("GBK")));
//            headers.set("charset", "GBK"); // 设置请求头的字符集为GBK
//
//            // 创建HttpEntity
//            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//
//            // 使用RestTemplate发送POST请求
////            String response = restTemplate.postForObject(url, entity, String.class);
////            System.out.println("Response from server: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}
