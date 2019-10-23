package com.javarush.task.task39.task3909;

import java.util.Arrays;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {
        if ((first.isEmpty() && second.isEmpty()) || first.equals(second)) return true;
        if (Math.abs(first.length() - second.length()) > 1) return false;
        return calculate(first, second) <= 1;
    }

    private static int calculate(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++)
            for (int j = 0; j <= y.length(); j++)
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1)
                            , y.charAt(j - 1))
                            , dp[i - 1][j] + 1
                            , dp[i][j - 1] + 1);
                }
        return dp[x.length()][y.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}
