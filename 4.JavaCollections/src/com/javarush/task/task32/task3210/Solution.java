package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int pos = Integer.parseInt(args[1]);
        String text = args[2];
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            byte[] textFromFile = new byte[text.getBytes().length];
            raf.seek(pos);
            raf.read(textFromFile, 0, textFromFile.length);
            raf.seek(raf.length());
            if (text.equals(new String(textFromFile)))
                raf.write("true".getBytes());
            else raf.write("false".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
