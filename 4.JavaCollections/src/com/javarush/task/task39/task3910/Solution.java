package com.javarush.task.task39.task3910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/*
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while (true) System.out.println(isPowerOfThree(Integer.parseInt(reader.readLine())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        for (int i = 1; i <= n; i *= 3)
            if (i == n) return true;
        return false;
    }
}
