package com.javarush.task.task20.task2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] armStrong = new long[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084,
                548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208,
                472335975, 534494836, 912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L,
                42678290603L, 44708635679L, 49388550606L, 82693916578L, 94204591914L, 28116440335967L,
                4338281769391370L, 4338281769391371L, 21897142587612075L, 35641594208964132L, 35875699062250035L,
                1517841543307505039L, 3289582984443187032L, 4498128791164624869L, 4929273885928088826L
        };
        List<Long> list = new ArrayList<>();
        for (long l : armStrong)
            if (l < N) list.add(l);
        long[] result = new long[list.size()];
        for (int i = 0; i < result.length; i++)
            result[i]=list.get(i);
        return result;
    }

    public static long getSum(long N) {
        long sum = 0L;
        String num = Long.toString(N);
        for (int i = 0; i < num.length(); i++)
            sum += Math.pow((double) Integer.parseInt(num.substring(i, i + 1)), (double) num.length());
        return sum;
    }

    public static boolean isPass(long l) {
        String str = Long.toString(l);
        if (str.length() == 1)
            return true;
        for (int i = 1; i < str.length(); i++) {
            if (str.substring(i,i+1).equals("0")) continue;
            if (Integer.parseInt(str.substring(i - 1, i)) > Integer.parseInt(str.substring(i, i + 1))) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long[] res = getNumbers(Long.parseLong(reader.readLine()));
        for (long re : res) System.out.println(re + ", " + getSum(re));
    }
}