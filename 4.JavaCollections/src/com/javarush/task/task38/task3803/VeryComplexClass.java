package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = Integer.valueOf(1);
        String s = (String) i;
    }

    public void methodThrowsNullPointerException() {
        Object[] kek = new Object[1];
        kek[0].toString();
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
