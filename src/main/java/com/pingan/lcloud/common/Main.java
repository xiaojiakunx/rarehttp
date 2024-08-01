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
            // ����URL���Ƴ�ĩβ�ĵ�����
            String urlStr = "http://192.168.1.209:1014/rare/word";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // �������󷽷���ͷ��Ϣ
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=GBK");

            // ����������
            String jsonData = "{\"id\": \"8\", \"name\": \"������\", \"yearold\": 18, \"opcode\": \"10140814\"}";
            byte[] dataBytes = jsonData.getBytes("GBK");

            // ��������
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(dataBytes, 0, dataBytes.length);
            }

            // ��ȡ��Ӧ
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
//        String dataStr = "{\"id\": \"3\", \"name\": \"������\", \"yearold\": 18, \"opcode\": \"10140814\"}";
//        byte[] dataBytes = dataStr.getBytes(StandardCharsets.ISO_8859_1); // ʹ��ISO-8859-1���룬��ΪGBK��Java�в�ֱ��֧��
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", "application/json; charset=ISO-8859-1") // ����Java�в�ֱ��֧��GBK������ʹ��ISO-8859-1����
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


//        String dataStr = "ʾ���ı�����";    // ��������
//        String targetURL = "http://example.com"; // Ŀ��URL
//
//        // ʹ��GBK���б���
//        byte[] data  = dataStr.getBytes(StandardCharsets.UTF_8);
//
//        HttpURLConnection connection = null;
//
//        try {
//            URL url = new URL(targetURL);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true); // ����������
//            connection.setDoOutput(true); // ���������
//            connection.setUseCaches(false); // ��������
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            connection.setRequestProperty("Charset", "GBK");
//            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//            // ���������
//            OutputStream outStream = connection.getOutputStream();
//            DataOutputStream dos = new DataOutputStream(outStream);
//            dos.write(data);   // д��������ַ���
//            dos.flush();
//            dos.close();
//            outStream.close();
//
//            // ��ȡ��Ӧ��
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


//        System.out.println("���! ������");
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://192.168.1.209:1014/controller"; // ��˿�������URL
//        String data = "Ф��"; // ��Ҫ���͵��ַ���
//        System.out.println("Ф��");
//
//        try {
//            // ʹ��URLEncoder����GBK�ַ���
//            String encodedData = URLEncoder.encode(data, "GBK");
//            System.out.println(data);
//
//            // ����������
//            String requestBody = "data=" + encodedData;
//
//            // ����HttpHeaders�������ַ���ΪGBK
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(new MediaType("application","x-www-form-urlencoded", Charset.forName("GBK")));
//            headers.set("charset", "GBK"); // ��������ͷ���ַ���ΪGBK
//
//            // ����HttpEntity
//            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//
//            // ʹ��RestTemplate����POST����
////            String response = restTemplate.postForObject(url, entity, String.class);
////            System.out.println("Response from server: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}
