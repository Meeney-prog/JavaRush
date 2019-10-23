package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int maxLength = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (!sb.toString().contains(s.substring(j, j + 1))) {
                    sb.append(s.charAt(j));
                    continue;
                }
                if (sb.length() > maxLength)
                    maxLength = sb.length();
                sb = new StringBuilder();
            }
            if (sb.length() > maxLength)
                maxLength = sb.length();
            sb = new StringBuilder();
        }
        if (sb.length() > maxLength)
            maxLength = sb.length();
        return maxLength;
    }
}
