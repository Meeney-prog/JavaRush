package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName1);
        FileOutputStream outputStream = new FileOutputStream(fileName2);
        byte[] buffer = new byte[inputStream.available()];
        int count = inputStream.read(buffer);
        inputStream.close();
        outputStream.write(buffer, 0, count - count / 2);
        outputStream.close();
        outputStream = new FileOutputStream(fileName3);
        outputStream.write(buffer, count - count / 2, count % 2 == 0 ? count - count / 2 : count - count / 2 - 1);
        outputStream.close();

    }
}
