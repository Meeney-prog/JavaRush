package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);
        byte[] file1 = new byte[inputStream.available()];
        inputStream.read(file1);
        inputStream.close();
        inputStream = new FileInputStream(fileName2);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] file2 = new byte[inputStream.available()];
        inputStream.read(file2);
        outputStream.write(file2);
        outputStream.write(file1);
        inputStream.close();
        outputStream.close();
    }
}
