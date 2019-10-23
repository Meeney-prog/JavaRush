package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        reader.close();
        reader.readLine();
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
