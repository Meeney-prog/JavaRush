package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty())
                break;
            String s1 = reader.readLine();
            if (s1.isEmpty()) {
                map.put("", Integer.parseInt(s));
                break;
            }
            map.put(s1, Integer.parseInt(s));
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            System.out.println(pair.getValue() + " " + pair.getKey());
        }
    }
}
