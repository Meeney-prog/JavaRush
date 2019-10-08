package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int[] array = {1, 3, 9, 27, 81, 243, 729, 2187};
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            if (number % 3 == 0) {
                sb.append("0");
                number /= 3;
            } else if (number % 3 == 1) {
                sb.append("+");
                number /= 3;
            } else {
                sb.append("-");
                number /= 3;
                number++;
            }
        }
        int value = 0;
        StringBuilder resultExpr = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '+') {
                resultExpr.append(" + ").append(array[i]);
                value += array[i];
            } else if (sb.charAt(i) == '-') {
                resultExpr.append(" - ").append(array[i]);
                value -= array[i];
            }
        }
        System.out.println(value + " =" + resultExpr);
    }
}