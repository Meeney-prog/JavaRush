package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] letersLowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] letersUpperCase = String.valueOf(letersLowerCase).toUpperCase().toCharArray();
        char[][] acceptedChars = {numbers, letersLowerCase, letersUpperCase};
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Random random = new Random();
        byteArrayOutputStream.write(acceptedChars[0][random.nextInt(acceptedChars[0].length)]);
        byteArrayOutputStream.write(acceptedChars[1][random.nextInt(acceptedChars[1].length)]);
        byteArrayOutputStream.write(acceptedChars[2][random.nextInt(acceptedChars[2].length)]);
        for (int i = 0; i <5; i++) {
            int indexOfArray = random.nextInt(acceptedChars.length);
            int indexOfChar = random.nextInt(acceptedChars[indexOfArray].length);
            byteArrayOutputStream.write(acceptedChars[indexOfArray][indexOfChar]);
        }
        return byteArrayOutputStream;
    }
}