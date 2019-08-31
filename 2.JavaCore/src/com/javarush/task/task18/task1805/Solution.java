package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileReader = new FileInputStream(fileName);
        int[] bytes = new int[256];
        while (fileReader.available() > 0) {
            bytes[fileReader.read()]++;
        }
        fileReader.close();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] > 0) {
                System.out.print(i + " ");
            }
        }
    }
}
