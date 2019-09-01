package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        while (reader.ready()){
            String[] words = reader.readLine().split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].matches("(\\D*\\d+\\D*)+")){
                    writer.write(words[i]+" ");
                }
            }
        }
        writer.close();
        reader.close();
    }
}
