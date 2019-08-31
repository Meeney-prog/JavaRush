package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        while (reader.ready()) {
            String s = reader.readLine();
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                writer.write(s.substring(matcher.start(), matcher.end()) + " ");
            }
        }
        reader.close();
        writer.close();
    }
}
