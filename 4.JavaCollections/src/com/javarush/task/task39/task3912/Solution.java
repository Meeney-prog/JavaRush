package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static int maxSquare(int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (i * j == 0)
                    continue;
                if (matrix[i][j] == 1)
                    matrix[i][j] = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1])) + 1;
                if (matrix[i][j] > result)
                    result = matrix[i][j];
            }
        return result * result;
    }
}
