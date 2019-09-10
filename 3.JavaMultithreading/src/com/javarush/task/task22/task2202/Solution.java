package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        try {
            System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        } catch (TooShortStringException e) {
            e.printStackTrace();
        }
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string==null){
            throw new TooShortStringException();
        }
        String[] words = string.split(" ");
        if (words.length < 5)
            throw new TooShortStringException();
        String s = "";
        int count = 0;
        for (int i = 1; i < words.length; i++) {
            if (count < 3) {
                s += words[i] + " ";
                count++;
            } else if (count == 3) {
                s += words[i];
                break;
            }

        }
        return s;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
