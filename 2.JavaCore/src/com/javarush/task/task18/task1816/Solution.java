package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream(args[0]);
        int count = 0;
        while (fileReader.available() > 0) {
            int b = fileReader.read();
            if (b > 64 && b < 91 || b > 96 && b < 123) {
                count++;
            }
        }
        fileReader.close();
        System.out.println(count);
    }
}
