package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = {13, 8, 15, 5, 17, 14};
        sort(array);
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        double mid;
        if (array.length % 2 == 1) {
            mid = array[array.length / 2];
        } else {
            mid = (double)(array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        }
        Comparator<Integer> comparator = (o1, o2) -> {
            double result = Math.abs(mid - o1) - Math.abs(mid - o2);
            if (result != 0) {
                return (int) Math.round(result);
            } else {
                return o1 - o2;
            }
        };
        //implement logic here
        Arrays.sort(array, comparator);
        return array;
    }
}
