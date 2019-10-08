package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            if (args[0].matches("\\w*")) {
                for (int i = 2; i <= 36; i++) {
                    try {
                        BigInteger number = new BigInteger(args[0], i);
                        System.out.println(i);
                        break;
                    } catch (Exception e) {
                    }
                }
            } else System.out.println("incorrect");
        } catch (Exception e) {
        }
    }
}