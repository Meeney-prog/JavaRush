package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int result = 0;
        int last = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.substring(i, i + 1).toUpperCase()) {
                case "I":
                    result = processDecimal(1, last, result);
                    last = 1;
                    break;
                case "V":
                    result = processDecimal(5, last, result);
                    last = 5;
                    break;
                case "X":
                    result = processDecimal(10, last, result);
                    last = 10;
                    break;
                case "L":
                    result = processDecimal(50, last, result);
                    last = 50;
                    break;
                case "C":
                    result = processDecimal(100, last, result);
                    last = 100;
                    break;
                case "D":
                    result = processDecimal(500, last, result);
                    last = 500;
                    break;
                case "M":
                    result = processDecimal(1000, last, result);
                    last = 1000;
                    break;
            }
        }
        return result;
    }

    public static int processDecimal(int value, int last, int result) {
        if (last > value) return result - value;
        else return result + value;
    }
}
