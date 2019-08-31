package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String filename = reader.readLine();
            if (filename.equals("exit")) {
                break;
            }
            ReadThread readThread = new ReadThread(filename);
            readThread.start();
        }

    }

    public static class ReadThread extends Thread {
        public String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }

        // implement file reading here - реализуйте чтение из файла тут
        public void run() {
            int[] bytes = new int[256];
            int max = 0;
            int maxI = 0;
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                while (inputStream.available() > 0) {
                    int b = inputStream.read();
                    bytes[b]++;
                    if (bytes[b] > max) {
                        maxI = b;
                        max = bytes[b];
                    }
                }
                resultMap.put(fileName, maxI);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
