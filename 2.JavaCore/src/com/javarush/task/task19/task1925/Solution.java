package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        List<String> lines = new ArrayList<>();
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        reader.close();
        boolean flag = false;
        for (int j = 0; j < lines.size(); j++) {
            String line = lines.get(j);
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 6) {
                    if (!flag) {
                        flag = true;
                        writer.write(words[i]);
                        continue;
                    }
                    writer.write("," + words[i]);
                }
            }
        }
        writer.close();
    }
}
