package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber == "") return false;
        return (telNumber.matches("\\+?(\\d{2})?\\(?(\\d{3})\\)?(\\d{3}[-]?\\d{2}[-]?\\d{2})"));
    }

    public static void main(String[] args) {

    }
}
