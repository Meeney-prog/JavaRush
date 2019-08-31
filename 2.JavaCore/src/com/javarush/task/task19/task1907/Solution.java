package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileName);
        String line = "";
        while (fileReader.ready()) {
            line += (char) fileReader.read();
        }
        fileReader.close();
        String line2 = line.replaceAll("\\bworld\\b", "");
        int count = (line.length() - line2.length()) / 5;
        System.out.println(count);
    }
}
