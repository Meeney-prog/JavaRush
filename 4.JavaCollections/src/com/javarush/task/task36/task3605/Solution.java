package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.System.in;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            StringBuilder text = new StringBuilder();
            while (reader.ready())
                text.append(reader.readLine());
            String str = new String(text);
            str = str.replaceAll("\\s", "").replaceAll("\\d", "").replaceAll("\\W", "");
            Set<String> set = new TreeSet<>();
            for (int i = 0; i < str.length(); i++)
                set.add(str.substring(i, i + 1).toLowerCase());
            int count = 0;
            for (String s: set) {
                if (count++ >= 5)
                    break;
                else System.out.print(s);
            }
        }
    }
}