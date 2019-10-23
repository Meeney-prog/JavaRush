package com.javarush.task.task39.task3908;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while (true) System.out.println(isPalindromePermutation(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.isEmpty()) return true;
        int[] letters = new int[256];
        boolean isThereOdd = false;
        for (char c : s.toLowerCase().toCharArray())
            letters[c]++;
        for (int i : letters)
            if (i % 2 == 1)
                if (isThereOdd) return false;
                else isThereOdd = true;
        return true;
    }
}
