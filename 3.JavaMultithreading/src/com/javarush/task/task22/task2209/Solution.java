package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        String file = "";
        while (reader.ready()) {
            file += reader.readLine() + " ";
        }
        reader.close();
        String[] words = file.trim().split(" ");
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) {
            return new StringBuilder();
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
        }
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        while (!flag) {
            flag = true;
            Collections.shuffle(list);
            sb = new StringBuilder(list.get(0) + " ");
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).substring(0, 1).toLowerCase().equals(list.get(i - 1).substring(list.get(i - 1).length() - 1).toLowerCase())) {
                    sb.append(list.get(i));
                    sb.append(" ");
                } else {
                    flag = false;
                    break;
                }
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb;
    }
}
