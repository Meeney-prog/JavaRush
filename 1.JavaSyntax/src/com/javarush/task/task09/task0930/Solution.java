package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        // напишите тут ваш код
        ArrayList<Integer> numI = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<String> strs = new ArrayList<>();
        ArrayList<Integer> strI = new ArrayList<>();
        boolean flag = true;

        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                nums.add(Integer.parseInt(array[i]));
                numI.add(i);
            } else {
                strs.add(array[i]);
                strI.add(i);
            }
        }
        Collections.sort(nums);
        Collections.reverse(nums);

        while (true) {
            if (!flag)
                break;
            flag = false;
            for (int i = 1; i < strs.size(); i++) {
                if (isGreaterThan(strs.get(i - 1), strs.get(i))) {
                    flag = true;
                    Collections.swap(strs, i, i - 1);
                }
            }
        }
        for (int i = 0; i < numI.size(); i++) {
            array[numI.get(i)] = nums.get(i).toString();
        }
        for (int i = 0; i < strI.size(); i++) {
            array[strI.get(i)] = strs.get(i);
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
