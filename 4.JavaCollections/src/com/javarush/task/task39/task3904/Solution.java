package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.List;

/*
Лестница
*/
public class Solution {
    private static int n = 70;
    private static List<Long> result = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n+1));
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n+2));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;
        if (result.size() >= n + 1 && result.get(n) != null) return result.get(n);
        for (int i = result.size(); i <= n; i++) {
            if (result.size() < i + 1 || result.get(i) != null) {
                if (i == 0 || i == 1) {
                    result.add(1L);
                    continue;
                }
                if (i == 2) {
                    result.add(2L);
                    continue;
                }
                if (i == 3) {
                    result.add(4L);
                    continue;
                }
                result.add(result.get(i - 1) + result.get(i - 2) + result.get(i - 3));
            }
        }
        return result.get(n);
    }
}

