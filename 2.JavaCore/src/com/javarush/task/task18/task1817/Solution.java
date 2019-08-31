package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        int spaceCoutn = 0;
        int allCount = 0;
        while (inputStream.available() > 0) {
            if (inputStream.read() == 32) {
                spaceCoutn++;
            }
            allCount++;
        }
        inputStream.close();
        System.out.printf("%.2f", (double) 100 * spaceCoutn / allCount);
    }
}
