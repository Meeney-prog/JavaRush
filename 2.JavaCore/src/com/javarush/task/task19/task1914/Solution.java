package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        PrintStream Sout = System.out;
        System.setOut(printStream);
        testString.printSomething();
        System.setOut(Sout);
        String result = byteArrayOutputStream.toString();
        System.out.println(result + Result(byteArrayOutputStream));
    }

    public static int Result(ByteArrayOutputStream byteArrayOutputStream) {
        String[] s = byteArrayOutputStream.toString().split(" ");
        switch (s[1]) {
            case "+":
                return Integer.parseInt(s[0]) + Integer.parseInt(s[2]);
            case "-":
                return Integer.parseInt(s[0]) - Integer.parseInt(s[2]);
            case "*":
                return Integer.parseInt(s[0]) * Integer.parseInt(s[2]);
        }
        return 0;
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

