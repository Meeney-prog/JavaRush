package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileNmae2 = reader.readLine();
        FileReader fileReader = new FileReader(fileName1);
        FileWriter fileWriter = new FileWriter(fileNmae2);
        reader.close();
        int count = 1;
        while (fileReader.ready()) {
            int character = fileReader.read();
            if (count % 2 == 0) {
                fileWriter.write(character);
            }
            count++;
        }
        fileReader.close();
        fileWriter.close();
    }

}
