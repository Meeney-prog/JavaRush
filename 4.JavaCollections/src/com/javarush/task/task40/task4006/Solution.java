package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try (Socket socket = new Socket(url.getHost(), url.getDefaultPort());
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String request = "GET " + url.getFile() + " HTTP/1.1\r\n" + "Host: " + url.getHost() + "\r\n\r\n";
            outputStream.write(request.getBytes());
            outputStream.flush();
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null)
                System.out.println(responseLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}