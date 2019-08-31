package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileReader = new FileInputStream(fileName);
        int[] bytes = new int[256];
        int min = 255;
        while (fileReader.available() > 0) {
            int curr = fileReader.read();
            bytes[curr]++;
            if (bytes[curr] < min) {
                min = bytes[curr];
            }
        }
        fileReader.close();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == min) {
                System.out.print(i + " ");
            }
        }
    }
}
