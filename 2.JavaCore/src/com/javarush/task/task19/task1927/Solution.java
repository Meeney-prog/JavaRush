package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
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
        String result = byteArrayOutputStream.toString().replaceAll("\r", "");
        String copy = "";
        int count = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i)=='\n') {
                count++;
                if (count % 2 == 0) {
                    copy += "\nJavaRush - курсы Java онлайн\n";
                    continue;
                }
            }
            copy += result.substring(i, i + 1);
        }
        System.out.println(copy);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
