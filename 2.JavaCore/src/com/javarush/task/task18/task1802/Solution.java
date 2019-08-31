package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileReader = new FileInputStream(fileName);
        int min = 255;
        while (fileReader.available() > 0) {
            int curr = fileReader.read();
            if (min > curr) {
                min = curr;
            }
        }
        fileReader.close();
        System.out.println(min);
    }
}
