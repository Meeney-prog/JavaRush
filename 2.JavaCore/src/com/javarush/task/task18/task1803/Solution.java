package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileReader = new FileInputStream(fileName);
        int[] bytes = new int[256];
        int maxByteIndex = 0;
        int max = 0;
        while (fileReader.available() > 0) {
            int curr = fileReader.read();
            bytes[curr]++;
            if (bytes[curr] > max) {
                max = bytes[curr];
                maxByteIndex = curr;
            }
        }
        fileReader.close();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == bytes[maxByteIndex]) {
                System.out.print(i + " ");
            }
        }
    }
}
