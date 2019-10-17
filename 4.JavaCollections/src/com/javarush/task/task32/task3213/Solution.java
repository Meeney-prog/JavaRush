package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] text = new char[4096];
        text = Arrays.copyOf(text, reader.read(text));
        for (char c : text)
            sb.append((char) (c + key));
        return sb.toString();
    }
}
