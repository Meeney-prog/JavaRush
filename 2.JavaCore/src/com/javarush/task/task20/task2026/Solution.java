package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j]==1){
                    count++;
                    turnOff(a, i, j);
                }
            }
        }
        return count;
    }
    public static void turnOff(byte[][] a, int i, int j){
        a[i][j] = 0;
        if (i+1 < a[0].length && a[i+1][j]==1){
            turnOff(a,i+1,j);
        }
        if (j+1 < a[0].length && a[i][j+1]==1){
            turnOff(a,i,j+1);
        }

    }
}
