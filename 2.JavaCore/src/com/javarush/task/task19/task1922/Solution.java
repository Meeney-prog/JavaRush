package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()){
            int count = 0;
            String line = reader.readLine();
            String[] stringWords = line.split("\\b? \\b?");
            for (int i = 0; i < stringWords.length; i++) {
                if (words.contains(stringWords[i])){
                    count++;
                }
            }
            if (count==2){
                System.out.println(line);
            }
        }
        reader.close();
    }
}
